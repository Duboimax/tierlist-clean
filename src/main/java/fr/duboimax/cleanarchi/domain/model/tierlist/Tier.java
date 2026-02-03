package fr.duboimax.cleanarchi.domain.model.tierlist;

public enum Tier {

    S("Les chefs-d'œuvre du branding"),
    A("Très bons logos"),
    B("Ça passe"),
    C("Médiocres"),
    D("Les flops visuels");

    private final String label;

    Tier(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
