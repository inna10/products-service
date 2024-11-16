package inna.com.inna_eisen_products_service;

import inna.com.inna_eisen_products_service.models.Product;
import inna.com.inna_eisen_products_service.repository.ProductRepository;
import inna.com.inna_eisen_products_service.services.APIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)// Otherwise mochito cries about my findAll stabbing
class InnaEisenProductsServiceApplicationTests {
	@Mock
	private ProductRepository taskRepository;
	APIService apiService;
	List<Product> repo=new ArrayList();
	@BeforeEach
	void initUseCase() {
		apiService = new APIService(taskRepository);
		repo.clear();
		when(taskRepository.saveAndFlush(any(Product.class))).thenAnswer(
				args -> {
							Product p=(Product)(args.getArgument(0));
							return emulateAddOperation(p);
				});

		when(taskRepository.count()).thenAnswer(new Answer<Long>() {
			public Long answer(InvocationOnMock invocation) throws Throwable {
				return (long)(repo.size());
			}});

		when(taskRepository.findAll()).thenAnswer(new Answer<List<Product>>() {
			public List<Product> answer(InvocationOnMock invocation) throws Throwable {
				return repo;
			}});

	}
	private Product emulateAddOperation(Product product) {
		product.setId(UUID.randomUUID());
		repo.add(product);
		return product;
	}


	@Test
	/**
	 * Tests that service method saves Product object 'as is' into repo
	 * and returns saved object without fields modifications. The only modified field - is generated 'id'
	 */
	public void addProduct_Success() throws Exception {
		Product product = new Product();
		product.setBarcode("bc1");
		product.setName("name1");
		product.setRating(1.0);
		product.setPrice(100.0);
		product.setTags(Arrays.asList(new String[]{"tag1","tag2"}));
		Product savedProduct =  apiService.createProduct(product);
		assertThat (savedProduct.getRating().equals(product.getRating())).isTrue();
		assertThat (savedProduct.getBarcode().equals(product.getBarcode())).isTrue();
		assertThat (savedProduct.getName().equals(product.getName())).isTrue();
		assertThat (savedProduct.getTags().equals(product.getTags())).isTrue();
		assertThat (savedProduct.getId().equals(product.getTags())).isFalse();// saved objects has generated Id
	}
	@Test
	/**
	 * Tests that service method saves Product object 'as is' into repo
	 * and returns saved object without fields modifications. The only modified field - is generated 'id'
	 */
	public void getAllProducts_Success() throws Exception {
		final int NUMBER_OF_PRODUCTS = 10;
		fillRepoByProducts(NUMBER_OF_PRODUCTS);
		assertThat (taskRepository.count()==NUMBER_OF_PRODUCTS).isTrue();//yes, repo is filled as expected
		List<Product> products = apiService.getProducts();
		assertThat (products.size()==NUMBER_OF_PRODUCTS).isTrue();//yes, getProducts returned full repo content

	}

	private void fillRepoByProducts(int productsNumber) throws Exception {
		for (int i = 0; i < productsNumber; i++) {
			Product product = new Product();
			product.setBarcode("bc"+i);
			product.setName("name"+i);
			product.setRating(1.0*i);
			product.setPrice(100.0*i);
			product.setTags(Arrays.asList(new String[]{"tag1","tag2"}));
			apiService.createProduct(product);
		}
	}
}
