package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.util.Objects;

@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO paramsDTO) {
        return titleCont(paramsDTO.getTitleCont())
                .and(categoryId(paramsDTO.getCategoryId()))
                .and(priceLt(paramsDTO.getPriceLt()))
                .and(priceGt(paramsDTO.getPriceGt()))
                .and(ratingGt(paramsDTO.getRatingGt()));
    }

    public Specification<Product> titleCont(String titleCont) {
        return ((root, query, criteriaBuilder) ->
                Objects.isNull(titleCont) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(root.get("title"), "%" + titleCont + "%"));
    }

    public Specification<Product> categoryId(Long categoryId){
        return (root, query, criteriaBuilder) ->
                Objects.isNull(categoryId) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public Specification<Product> priceLt(Integer priceLt){
        return (root, query, criteriaBuilder) ->
                Objects.isNull(priceLt) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    public Specification<Product> priceGt(Integer priceGt){
        return (root, query, criteriaBuilder) ->
                Objects.isNull(priceGt) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    public Specification<Product> ratingGt(Double ratingGt){
        return (root, query, criteriaBuilder) ->
                Objects.isNull(ratingGt) ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }
}