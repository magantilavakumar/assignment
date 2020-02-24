package com.assignment.services.fetchers.util;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomerTrxOffSetLimitRequest implements Pageable, Serializable {

    private static final long serialVersionUID = -25822477129613575L;

    private int limit;
    private int offset;
    private final Sort sort;

    /**
     * Creates a new {@link CustomerTrxOffSetLimitRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     * @param sort   can be {@literal null}.
     */
    public CustomerTrxOffSetLimitRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    /**
     * Creates a new {@link CustomerTrxOffSetLimitRequest} with sort parameters applied.
     *
     * @param offset     zero-based offset.
     * @param limit      the size of the elements to be returned.
     * @param direction  the direction of the {@link Sort} to be specified, can be {@literal null}.
     * @param properties the properties to sort by, must not be {@literal null} or empty.
     */
    public CustomerTrxOffSetLimitRequest(int offset, int limit, Sort.Direction direction, String... properties) {
        this(offset, limit, new Sort(direction, properties));
    }

    /**
     * Creates a new {@link CustomerTrxOffSetLimitRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     */
    public CustomerTrxOffSetLimitRequest(int offset, int limit) {
        this(offset, limit, new Sort(Sort.Direction.ASC, "id"));
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new CustomerTrxOffSetLimitRequest((int) getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public CustomerTrxOffSetLimitRequest previous() {
        return hasPrevious() ? new CustomerTrxOffSetLimitRequest((int) getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new CustomerTrxOffSetLimitRequest(0, getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("limit", limit)
                .append("offset", offset)
                .append("sort", sort)
                .toString();
    }
}
