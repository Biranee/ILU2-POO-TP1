package produit;

public enum Unite {
    GRAMME("g"),
    KILOGRAMME("kg"),
    LITRE("l"),
    CENTILITRE("cl"),
    MILLILITRE("ml"),
    PIECE("pièce");

    private final String symbole;

    Unite(String symbole) {
        this.symbole = symbole;
    }

    public String getSymbole() {
        return symbole;
    }
}
