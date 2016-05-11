package backend.server.rethink;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Json;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public abstract class RethinkRepository<T, ID, Converter extends JsonConverter<T, ID>> {
    protected CompletableFutureFactory completableFutureFactory;

    protected RethinkDB r;
    protected Connection connection;

    protected String tableName;

    protected Converter converter;

    public RethinkRepository(Converter converter, RethinkDB r, Connection connection, String dbName, String tableName,
                             CompletableFutureFactory completableFutureFactory) {
        this.converter = converter;
        this.r = r;
        this.completableFutureFactory = completableFutureFactory;

        connection.use(dbName);
        this.connection = connection;
        this.tableName = tableName;
    }

    public CompletableFuture<T> findOne(CompletableFuture<ID> id, Class<T> clazz) {
        CompletableFuture<T> resultCF = new CompletableFuture<>();

        id.thenAccept(entityId -> {
            try {
                resultCF.complete(findOneInRethink(entityId, clazz));
            } catch (JsonProcessingException e) {
                resultCF.completeExceptionally(e);
            }
        });

        return resultCF;
    }

    private T findOneInRethink(ID entityId, Class<T> clazz) throws JsonProcessingException {
        T t = converter.convertMapToEntity(r
                .table(tableName)
                .get(getJson(entityId))
                .run(connection), clazz);

        return t;
    }

    public CompletableFuture<Collection<T>> findAll(CompletableFuture<Collection<ID>> ids,
                                                    Class<T> clazz) {
        CompletableFuture<Cursor<Map<String, Object>>> cursorOfMaps = convertCollectionOfIDs(ids)
                .thenApply(jsons -> findAllInRethink(jsons));

        CompletableFuture<Collection<T>> resultCF = cursorOfMaps.thenApply(maps -> maps
                .toList()
                .stream()
                .map(stringObjectMap -> converter.convertMapToEntity(stringObjectMap, clazz))
                .collect(Collectors.toList()));

        return resultCF;
    }

    private Cursor<Map<String, Object>> findAllInRethink(Collection<Json> jsons) {
        Cursor<Map<String, Object>> cursor = r
                .table(tableName)
                .getAll(jsons.toArray())
                .run(connection);

        return cursor;
    }

    public CompletableFuture<Collection<T>> findWithPagination(int page, int size,
                                                               Class<T> clazz) {

        CompletableFuture<List<Map<String, Object>>> list = completableFutureFactory
                .supply(() -> findWithPaginationInRethink(page, size));

        CompletableFuture<Collection<T>> resultCF = list.thenApply(maps -> maps
                .stream()
                .map(stringObjectMap -> converter.convertMapToEntity(stringObjectMap, clazz))
                .collect(Collectors.toList()));

        return resultCF;
    }

    private List<Map<String, Object>> findWithPaginationInRethink(int page, int size) {
        List<Map<String, Object>> list = r
                .table(tableName)
                .orderBy("id")
                .skip(page * size)
                .limit(size)
                .run(connection);
        return list;
    }

    public CompletableFuture<T> update(ID id, T entity) {
        throw new RuntimeException("not ready");
        // todo
    }

    public CompletableFuture<T> merge(ID id, Map<String, Object> attributes) {
        throw new RuntimeException("not ready");
        // todo
    }

    public CompletableFuture<Void> save(CompletableFuture<T> entity) {
        CompletableFuture<Void> resultCF = new CompletableFuture<>();

        entity.thenAccept(t -> {
            try {
                saveInRethink(t);
                resultCF.complete(null);
            } catch (JsonProcessingException e) {
                resultCF.completeExceptionally(e);
            }
        });

        return resultCF;
    }

    private void saveInRethink(T t) throws JsonProcessingException {
        r.table(tableName)
                .insert(r.json(converter.convertValueToJsonString(t)))
                .run(connection);
    }

    public CompletableFuture<Void> delete(CompletableFuture<ID> id) {
        CompletableFuture<Void> deleteFuture = new CompletableFuture<>();

        CompletableFuture<Void> resultCF = id.thenAccept(id1 -> {
            try {
                deleteOneFromRethink(id1);
                deleteFuture.complete(null);
            } catch (JsonProcessingException e) {
                deleteFuture.completeExceptionally(e);
            }
        });

        return resultCF;
    }

    private void deleteOneFromRethink(ID id) throws JsonProcessingException {
        r.table(tableName)
                .get(getJson(id))
                .delete()
                .run(connection);
    }

    public CompletableFuture<Void> deleteAll(CompletableFuture<Collection<ID>> ids) {
        CompletableFuture<Void> resultCF = convertCollectionOfIDs(ids).thenAccept(jsons -> {
            r.table(tableName).getAll(jsons.toArray()).delete().run(connection);
        });

        return resultCF;
    }

    private CompletableFuture<Collection<Json>> convertCollectionOfIDs(CompletableFuture<Collection<ID>> ids) {
        CompletableFuture<Collection<Json>> resultCF = new CompletableFuture<>();
        List<Json> list = new ArrayList<>();

        ids.thenAccept(ids1 -> {
            for (ID id : ids1) {
                try {
                    list.add(getJson(id));
                } catch (JsonProcessingException e) {
                    resultCF.completeExceptionally(e);
                    break;
                }
            }
        });

        if (!resultCF.isCompletedExceptionally()) {
            resultCF.complete(list);
        }

        return resultCF;
    }

    private Json getJson(ID id) throws JsonProcessingException {
        return r.json(converter.convertIDToJsonString(id));
    }
}
