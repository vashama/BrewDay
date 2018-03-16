package ser422.sneha.web.model;

public class Article {
    private String ArticleID;
    private String ArticleTitle;
    private String ArticleContent;
    private String accessSpecifier;
    private String ReporterId;

    public Article(String ArticleID, String ArticleTitle, String ArticleContent, String accessSpecifier, String ReporterId){
        this.ArticleID=ArticleID;
        this.ArticleTitle=ArticleTitle;
        this.ArticleContent=ArticleContent;
        this.accessSpecifier=accessSpecifier;
        this.ReporterId = ReporterId;
    }
    public Article(){};
    public String getArticleID() {
        return ArticleID;
    }

    public void setArticleID(String articleID) {
        ArticleID = articleID;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        ArticleTitle = articleTitle;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public void setArticleContent(String articleContent) {
        ArticleContent = articleContent;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    public void setAccessSpecifier(String accessSpecifier) {
        this.accessSpecifier = accessSpecifier;
    }

    public String getReporterId() {
        return ReporterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (!ArticleID.equals(article.ArticleID)) return false;
        if (!ArticleTitle.equals(article.ArticleTitle)) return false;
        if (!ArticleContent.equals(article.ArticleContent)) return false;
        if (!accessSpecifier.equals(article.accessSpecifier)) return false;
        return ReporterId.equals(article.ReporterId);
    }

    @Override
    public int hashCode() {
        int result = ArticleID.hashCode();
        result = 31 * result + ArticleTitle.hashCode();
        result = 31 * result + ArticleContent.hashCode();
        result = 31 * result + accessSpecifier.hashCode();
        result = 31 * result + ReporterId.hashCode();
        return result;
    }

    public void setReporterId(String reporterId) {
        ReporterId = reporterId;
    }



}
