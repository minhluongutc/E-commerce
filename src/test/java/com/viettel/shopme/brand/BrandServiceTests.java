package com.viettel.shopme.brand;

import com.viettel.shopme.admin.brand.BrandRepository;
import com.viettel.shopme.admin.brand.BrandService;
import com.viettel.shopme.common.entity.Brand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BrandServiceTests {

    @MockBean
    private BrandRepository repo;

    @InjectMocks
    private BrandService service;

    @Test
    public void testCheckUniqueInNewModeReturnDuplicate() {
        Integer id = null;
        String name = "Acer";
        Brand brand = new Brand(id, name);

        Mockito.when(repo.findByName(name)).thenReturn(brand);

        String result = service.checkUnique(id, name);
        assertThat(result).isEqualTo("Duplicate");
    }

    @Test
    public void testCheckUniqueInNewModeReturnOK() {
        Integer id = null;
        String name = "Acer";
        Brand brand = new Brand(id, name);

        Mockito.when(repo.findByName(name)).thenReturn(null);

        String result = service.checkUnique(id, name);
        assertThat(result).isEqualTo("OK");
    }

    @Test
    public void testCheckUniqueInEditModeReturnDuplicate() {
        Integer id = 1;
        String name = "Acer";
        Brand brand = new Brand(2, name);

        Mockito.when(repo.findByName(name)).thenReturn(brand);

        String result = service.checkUnique(id, name);
        assertThat(result).isEqualTo("Duplicate");
    }

    @Test
    public void testCheckUniqueInEditModeReturnOK() {
        Integer id = 1;
        String name = "Acer";
        Brand brand = new Brand(2, name);

        Mockito.when(repo.findByName(name)).thenReturn(null);

        String result = service.checkUnique(id, name);
        assertThat(result).isEqualTo("OK");
    }
}
