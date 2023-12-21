package com.sts.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {
	

	@Id
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private Integer price;
	
	@Column(name = "brand", nullable = false)
	private String brand;
	
	@Lob
	@Column(name = "description", nullable = false,columnDefinition = "TEXT",length = 5000)
	private String description;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
	@JsonIgnore
    List<Reviews> product_reviews = new ArrayList<Reviews>();
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_images",joinColumns= {@JoinColumn(name="product_id")},inverseJoinColumns= {@JoinColumn(name="image_id")})
	private Set<ImageModel> productImages;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<ImageModel> getProductImages() {
		return productImages;
	}
	public void setProductImages(Set<ImageModel> productImages) {
		this.productImages = productImages;
	}
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", brand=" + brand + ", description="
				+ description + "]";
	}
}
