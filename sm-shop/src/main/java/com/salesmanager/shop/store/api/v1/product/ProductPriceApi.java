package com.salesmanager.shop.store.api.v1.product;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableProductPrice;
import com.salesmanager.shop.model.catalog.product.ReadableProductPrice;
import com.salesmanager.shop.model.entity.Entity;
import com.salesmanager.shop.store.controller.product.facade.ProductPriceFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Use inventory
 * @author carlsamson
 *
 */

@Controller
@RequestMapping("/api/v1")
@Api(tags = { "Product price api" })
@SwaggerDefinition(tags = { @Tag(name = "Product price management", description = "Edit price and discount") })
public class ProductPriceApi {


	@Autowired
	private ProductPriceFacade productPriceFacade;;


	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApi.class);

	@ResponseStatus(HttpStatus.OK)
	@PostMapping({"/private/product/{sku}/inventory/{inventoryId}/price"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody Entity save(
			@PathVariable String sku,
			@PathVariable Long inventoryId,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		price.setSku(sku);
		price.setProductAvailabilityId(inventoryId);
		
		Long id = productPriceFacade.save(price, merchantStore);
		return new Entity(id);

		
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping({"/private/product/{sku}/price"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public @ResponseBody Entity save(
			@PathVariable String sku,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		price.setSku(sku);
		
		Long id = productPriceFacade.save(price, merchantStore);
		return new Entity(id);

		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping({"/private/product/{sku}/inventory/{inventoryId}/price/{priceId}"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void edit(
			@PathVariable String sku,
			@PathVariable Long inventoryId,
			@PathVariable Long priceId,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		
		price.setSku(sku);
		price.setProductAvailabilityId(inventoryId);
		price.setId(priceId);
		productPriceFacade.save(price, merchantStore);


		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping({"/private/product/{sku}/price/{priceId}"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public ReadableProductPrice get(
			@PathVariable String sku,
			@PathVariable Long priceId,
			@Valid @RequestBody PersistableProductPrice price,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		
		price.setSku(sku);
		price.setId(priceId);

		return productPriceFacade.get(sku, priceId, merchantStore, language);
	
	}
	
	@GetMapping({"/private/product/{sku}/inventory/{inventoryId}/price"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public List<ReadableProductPrice> list(
			@PathVariable String sku,
			@PathVariable Long inventoryId,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		
		return productPriceFacade.list(sku, inventoryId, merchantStore, language);

		
	}
	
	
	@GetMapping({"/private/product/{sku}/prices"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public List<ReadableProductPrice> list(
			@PathVariable String sku,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		
		return productPriceFacade.list(sku, merchantStore, language);

		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping({"/private/product/{sku}/price/{priceId}"})
	@ApiImplicitParams({ @ApiImplicitParam(name = "store", dataType = "String", defaultValue = "DEFAULT"),
			@ApiImplicitParam(name = "lang", dataType = "String", defaultValue = "en") })
	public void delete(
			@PathVariable String sku,
			@PathVariable Long priceId,
			@ApiIgnore MerchantStore merchantStore, 
			@ApiIgnore Language language) {
		
		
		productPriceFacade.delete(priceId, sku, merchantStore);
		
	}

}
