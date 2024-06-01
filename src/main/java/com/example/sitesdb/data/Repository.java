package com.example.sitesdb.data;

import java.util.List;

public interface Repository {
    boolean addWebsite(Website website);
    List<Website> getAll();
    boolean updateWebsite(int id, Website website);
    boolean deleteWebsite(int id);
    Website getWebsiteById(int id);
    List<Website> getWebsitesByCategory(String category);
    List<Website> searchWebsitesByDescription(String searchText);
}
