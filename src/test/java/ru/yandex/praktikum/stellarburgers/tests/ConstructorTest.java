package ru.yandex.praktikum.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.praktikum.stellarburgers.utils.BaseTest;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверяем, что работает переход к разделу 'Булки' в конструкторе")
    public void testNavigationToBunsSection() {
        mainPage.clickBunsTab();
        
        Assert.assertTrue("Раздел 'Булки' должен быть активным", 
                mainPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяем, что работает переход к разделу 'Соусы' в конструкторе")
    public void testNavigationToSaucesSection() {
        mainPage.clickSaucesTab();
        
        Assert.assertTrue("Раздел 'Соусы' должен быть активным", 
                mainPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяем, что работает переход к разделу 'Начинки' в конструкторе")
    public void testNavigationToFillingsSection() {
        mainPage.clickFillingsTab();
        
        Assert.assertTrue("Раздел 'Начинки' должен быть активным", 
                mainPage.isFillingsTabActive());
    }
}