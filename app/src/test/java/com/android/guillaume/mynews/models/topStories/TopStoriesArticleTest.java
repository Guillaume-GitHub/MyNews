package com.android.guillaume.mynews.models.topStories;

import com.android.guillaume.mynews.models.topStories.TopStoriesArticle;
import com.android.guillaume.mynews.models.topStories.TopStoriesMedia;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TopStoriesArticleTest {

    private TopStoriesArticle article = new TopStoriesArticle();

    @Test
    public void getSection() {
        String section = "movie";
        article.setSection(section);
        assertEquals(section,article.getSection());
    }

    @Test
    public void setSection() {
        String section = "movie";
        article.setSection(section);
        assertEquals(section,article.getSection());
    }

    @Test
    public void getSubsection() {
        String subSection = "series";
        article.setSubsection(subSection);
        assertEquals(subSection,article.getSubsection());
    }

    @Test
    public void setSubsection() {
        String subSection = "series";
        article.setSubsection(subSection);
        assertEquals(subSection,article.getSubsection());
    }

    @Test
    public void getTitle() {
        String title = "New season of GOT is coming";
        article.setTitle(title);
        assertEquals(title, article.getTitle());
    }

    @Test
    public void setTitle() {
        String title = "New season of GOT is coming";
        article.setTitle(title);
        assertEquals(title, article.getTitle());
    }

    @Test
    public void getAbstract() {
        String abstracts = "it's abstract ?";
        article.setAbstract(abstracts);
        assertEquals(abstracts, article.getAbstract());
    }

    @Test
    public void setAbstract() {
        String abstracts = "it's abstract ?";
        article.setAbstract(abstracts);
        assertEquals(abstracts, article.getAbstract());
    }

    @Test
    public void getUrl() {
        String url = "https://www.google.fr";
        article.setUrl(url);
        assertEquals(url, article.getUrl());
    }

    @Test
    public void setUrl() {
        String url = "https://www.google.fr";
        article.setUrl(url);
        assertEquals(url, article.getUrl());
    }

    @Test
    public void getByline() {
        String byLine = "Written by someone";
        article.setByline(byLine);
        assertEquals(byLine, article.getByline());
    }

    @Test
    public void setByline() {
        String byLine = "Written by someone";
        article.setByline(byLine);
        assertEquals(byLine, article.getByline());
    }

    @Test
    public void getItemType() {
        String itemType = "topStoriesArticle";
        article.setItemType(itemType);
        assertEquals(itemType, article.getItemType());
    }

    @Test
    public void setItemType() {
        String itemType = "topStoriesArticle";
        article.setItemType(itemType);
        assertEquals(itemType, article.getItemType());
    }

    @Test
    public void getUpdatedDate() {
        String date = "2019-01-24";
        article.setUpdatedDate(date);
        assertEquals(date, article.getUpdatedDate());
    }

    @Test
    public void setUpdatedDate() {
        String date = "2019-01-24";
        article.setUpdatedDate(date);
        assertEquals(date, article.getUpdatedDate());
    }

    @Test
    public void getCreatedDate() {
        String date = "2019-01-24";
        article.setCreatedDate(date);
        assertEquals(date, article.getCreatedDate());
    }

    @Test
    public void setCreatedDate() {
        String date = "2019-01-24";
        article.setCreatedDate(date);
        assertEquals(date, article.getCreatedDate());
    }

    @Test
    public void getPublishedDate() {
        String date = "2019-01-24";
        article.setPublishedDate(date);
        assertEquals(date, article.getPublishedDate());
    }

    @Test
    public void setPublishedDate() {
        String date = "2019-01-24";
        article.setPublishedDate(date);
        assertEquals(date, article.getPublishedDate());
    }

    @Test
    public void getMaterialTypeFacet() {
        String materialTypeFacet = "material facet";
        article.setMaterialTypeFacet(materialTypeFacet);
        assertEquals(materialTypeFacet, article.getMaterialTypeFacet());
    }

    @Test
    public void setMaterialTypeFacet() {
        String materialTypeFacet = "material facet";
        article.setMaterialTypeFacet(materialTypeFacet);
        assertEquals(materialTypeFacet, article.getMaterialTypeFacet());
    }

    @Test
    public void getKicker() {
        String kicker = "kicker";
        article.setKicker(kicker);
        assertEquals(kicker, article.getKicker());
    }

    @Test
    public void setKicker() {
        String kicker = "kicker";
        article.setKicker(kicker);
        assertEquals(kicker, article.getKicker());
    }

    @Test
    public void getDesFacet() {
        List<String> desFacetList = new ArrayList<>();
        article.setDesFacet(desFacetList);
        assertEquals(desFacetList,article.getDesFacet());
    }

    @Test
    public void setDesFacet() {
        List<String> desFacetList = new ArrayList<>();
        article.setDesFacet(desFacetList);
        assertEquals(desFacetList,article.getDesFacet());
    }

    @Test
    public void getOrgFacet() {
        List<String> orgFacetList = new ArrayList<>();
        article.setOrgFacet(orgFacetList);
        assertEquals(orgFacetList,article.getOrgFacet());
    }

    @Test
    public void setOrgFacet() {
        List<String> orgFacetList = new ArrayList<>();
        article.setOrgFacet(orgFacetList);
        assertEquals(orgFacetList,article.getOrgFacet());
    }

    @Test
    public void getPerFacet() {
        List<Object> perFacetList = new ArrayList<>();
        article.setPerFacet(perFacetList);
        assertEquals(perFacetList,article.getPerFacet());
    }

    @Test
    public void setPerFacet() {
        List<Object> perFacetList = new ArrayList<>();
        article.setPerFacet(perFacetList);
        assertEquals(perFacetList,article.getPerFacet());
    }

    @Test
    public void getGeoFacet() {
        List<String> geoFacetList = new ArrayList<>();
        article.setGeoFacet(geoFacetList);
        assertEquals(geoFacetList,article.getGeoFacet());
    }

    @Test
    public void setGeoFacet() {
        List<String> geoFacetList = new ArrayList<>();
        article.setGeoFacet(geoFacetList);
        assertEquals(geoFacetList,article.getGeoFacet());
    }

    @Test
    public void getMultimedia() {
        List<TopStoriesMedia> multimediaList = new ArrayList<>();
        article.setMultimedia(multimediaList);
        assertEquals(multimediaList,article.getMultimedia());
    }

    @Test
    public void setMultimedia() {
        List<TopStoriesMedia> multimediaList = new ArrayList<>();
        article.setMultimedia(multimediaList);
        assertEquals(multimediaList,article.getMultimedia());
    }

    @Test
    public void getMultimediaCount() {
        List<TopStoriesMedia> multimediaList = new ArrayList<>(1);
        TopStoriesMedia media = new TopStoriesMedia();
        multimediaList.add(0,media);

        assertEquals(1, multimediaList.size());
    }

    @Test
    public void getShortUrl() {
        String shortUrl = "https://google.com";
        article.setShortUrl(shortUrl);
        assertEquals(shortUrl, article.getShortUrl());
    }

    @Test
    public void setShortUrl() {
        String shortUrl = "https://google.com";
        article.setShortUrl(shortUrl);
        assertEquals(shortUrl, article.getShortUrl());
    }
}