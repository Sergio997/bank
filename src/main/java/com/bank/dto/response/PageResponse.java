package com.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    private Integer totalPages;
    private Long totalElements;
    private List<T> data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageResponse<?> that = (PageResponse<?>) o;
        return Objects.equals(totalPages, that.totalPages) &&
                Objects.equals(totalElements, that.totalElements) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalPages, totalElements, data);
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", data=" + data +
                '}';
    }

}