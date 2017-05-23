package com.adifferentuniverse.discourse.submission.model;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Ratings {

    private Long submissionId;
    private List<Rating> ratings;

    public Ratings() {
        ratings = new ArrayList<>();
    }

    public Ratings(Rating... ratings) {
        this(Arrays.asList(ratings));
    }

    public Ratings(List<Rating> ratings) {
        this();
        this.ratings.addAll(ratings);
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public Float getAverageRating() {
        double average = ratings.stream()
                .filter(r -> r.getValue() != null)
                .mapToDouble(Rating::getValue)
                .average()
                .orElse(0);
        return (float) average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratings ratings1 = (Ratings) o;
        return Objects.equals(submissionId, ratings1.submissionId) &&
                Objects.equals(ratings, ratings1.ratings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(submissionId, ratings);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("submissionId", submissionId)
                .add("ratings", ratings)
                .toString();
    }
}
