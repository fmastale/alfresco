package automation.utils.loaders;

public enum Pages {
    LOGIN_PAGE(new EnvironmentConfigLoader("environment").getLoginPage()),
    ADMIN_TOOLS_GROUPS_PAGE(new EnvironmentConfigLoader("environment").getAdminToolsGroupPage()),
    BROWSE_GROUPS_PANEL(new EnvironmentConfigLoader("environment").getBrowseGroupsPanel()),
    NEW_GROUP_PAGE(new EnvironmentConfigLoader("environment").getNewGroupPage()),
    GROUP_EDIT_PAGE(new EnvironmentConfigLoader("environment").getGroupEditPage());

    private final String url;

    Pages(String otherUrl) {
        url = otherUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getConcreteUrl(String address) {
        String newUrl = url + address;
        return newUrl;
    }
}
