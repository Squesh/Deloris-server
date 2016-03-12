package server.core.hero.dto;

public enum HeroSubClass {
    BERSERKER(HeroClass.WARRIOR),
    MONK(HeroClass.WARRIOR),
    ARCHER(HeroClass.RANGER),
    ASSASSIN(HeroClass.RANGER),
    ELEMENTALIST(HeroClass.MAGE),
    PSIONIC(HeroClass.MAGE);

    private HeroClass parentClass;

    HeroSubClass(HeroClass parentClass) {
        this.parentClass = parentClass;
    }

    public HeroClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(HeroClass parentClass) {
        this.parentClass = parentClass;
    }
}
