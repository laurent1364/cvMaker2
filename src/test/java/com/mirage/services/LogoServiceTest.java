package com.mirage.services;

import com.mirage.domains.utils.Logo;
import com.mirage.utils.CreationUtils;
import com.mirage.utils.services.ServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Mirage on 03/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LogoServiceTest implements ServiceTest{

    private LogoService logoService;
    private CreationUtils creationUtils;

    @Autowired
    public void setLogoService(LogoService logoService) {
        this.logoService = logoService;
    }

    @Autowired
    public void setCreationUtils(CreationUtils creationUtils) {
        this.creationUtils = creationUtils;
    }

    @Override
    @Test
    public void save() throws Exception {
        Logo logo = new Logo();
        logo.setCssClass("testCssClass");
        logo.setHexaCode("testHexaCode");
        logo.setName("testName");

        Logo savedLogo = logoService.saveOrUpdate(logo);

        assert savedLogo.getId() != null;
    }

    @Override
    @Test
    public void listAll() throws Exception {

        creationUtils.createLogo();

        List<Logo> logos = (List<Logo>) logoService.listAll();

        assert logos.size() > 0;
    }

    @Override
    @Test
    public void getOne() throws Exception {

        String name = "logo";
        String logoClass = "fa-logo";
        String logoHexa = "0x1254";

        Logo savedLogo = logoService.saveOrUpdate(new Logo(name, logoClass, logoHexa));

        Integer id = savedLogo.getId();

        Logo getLogo = logoService.getById(id);

        assert getLogo.getId() != null;
        assert getLogo.getId() == id;
        assert getLogo.getName() != null;
        assert getLogo.getName().equals(name);
        assert getLogo.getCssClass() != null;
        assert getLogo.getCssClass().equals(logoClass);
        assert getLogo.getHexaCode() != null;
        assert getLogo.getHexaCode().equals(logoHexa);
    }

    @Override
    @Test
    public void update() throws Exception {

        Logo logo = creationUtils.createLogo();
        String name = "new Name";
        String cssClass = "new cssClass";
        String hexaCode = "new HexaCode";

        logo.setName(name);
        logo.setCssClass(cssClass);
        logo.setHexaCode(hexaCode);

        Logo savedLogo = logoService.saveOrUpdate(logo);

        assert savedLogo.getId() != null;
        assert savedLogo.getId().equals(logo.getId());
        assert savedLogo.getName() != null;
        assert savedLogo.getName().equals(name);
        assert savedLogo.getCssClass() != null;
        assert savedLogo.getCssClass().equals(cssClass);
        assert savedLogo.getHexaCode() != null;
        assert savedLogo.getHexaCode().equals(hexaCode);
    }

    @Override
    @Test
    public void delete() throws Exception {

        List<Logo> logos = (List<Logo>) logoService.listAll();
        int size = logos.size();

        logoService.delete(2);

        logos = (List<Logo>) logoService.listAll();

        assert logos.size() == size - 1;
    }
}
