package automation.utils.loaders;

public enum Pages {
    //todo: make it DRY
    LOGIN_PAGE("http://127.0.0.1:8080/share/page/"),
    GROUP_MANAGEMENT_PAGE("http://127.0.0.1:8080/share/page/console/admin-console/groups"),
    BROWSE_GROUPS_PAGE("http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dsearch%26refresh%3Dfalse"),
    NEW_GROUP_PAGE("http://127.0.0.1:8080/share/page/console/admin-console/groups#state=panel%3Dcreate");

    private final String url;

    Pages(String otherUrl) {
        url = otherUrl;
    }

    public String getUrl() {
        return url;
    }
}
