package com.example.pps_tudai.services.avatarService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class AvatarAPIResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("attributionText")
    @Expose
    private String attributionText;
    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        @SerializedName("offset")
        @Expose
        private Integer offset;
        @SerializedName("limit")
        @Expose
        private Integer limit;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("results")
        @Expose
        private List<Result> results;

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }
    }

    public class Result {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("modified")
        @Expose
        private String modified;
        @SerializedName("thumbnail")
        @Expose
        private Thumbnail thumbnail;
        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;
        @SerializedName("comics")
        @Expose
        private Event comics;
        @SerializedName("series")
        @Expose
        private Event series;
        @SerializedName("stories")
        @Expose
        private Storie stories;
        @SerializedName("events")
        @Expose
        private Event events;
        @SerializedName("urls")
        @Expose
        private List<UrlClass> urls;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public Event getComics() {
            return comics;
        }

        public void setComics(Event comics) {
            this.comics = comics;
        }

        public Event getSeries() {
            return series;
        }

        public void setSeries(Event series) {
            this.series = series;
        }

        public Storie getStories() {
            return stories;
        }

        public void setStories(Storie stories) {
            this.stories = stories;
        }

        public Event getEvents() {
            return events;
        }

        public void setEvents(Event events) {
            this.events = events;
        }

        public List<UrlClass> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlClass> urls) {
            this.urls = urls;
        }
    }

    public class Thumbnail {

        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("extension")
        @Expose
        private String extension;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

    public class Storie {

        @SerializedName("available")
        @Expose
        private int available;
        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;
        @SerializedName("items")
        @Expose
        private List<ItemStorie> items;
        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<ItemStorie> getItems() {
            return items;
        }

        public void setItems(List<ItemStorie> items) {
            this.items = items;
        }

        public int getReturned() {
            return returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }
    }

    public class ItemStorie {

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("type")
        @Expose
        private String type;

        public String getResourceURI() {
            return resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public class Event {

        @SerializedName("available")
        @Expose
        private int available;
        @SerializedName("collectionURI")
        @Expose
        private String collectionURI;
        @SerializedName("items")
        @Expose
        private List<ItemEvent> items;
        @SerializedName("returned")
        @Expose
        private int returned;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getCollectionURI() {
            return collectionURI;
        }

        public void setCollectionURI(String collectionURI) {
            this.collectionURI = collectionURI;
        }

        public List<ItemEvent> getItems() {
            return items;
        }

        public void setItems(List<ItemEvent> items) {
            this.items = items;
        }

        public int getReturned() {
            return returned;
        }

        public void setReturned(int returned) {
            this.returned = returned;
        }
    }

    public class ItemEvent {

        @SerializedName("resourceURI")
        @Expose
        private String resourceURI;
        @SerializedName("name")
        @Expose
        private String name;

        public String getResourceURI() {
            return resourceURI;
        }

        public void setResourceURI(String resourceURI) {
            this.resourceURI = resourceURI;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class UrlClass {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
