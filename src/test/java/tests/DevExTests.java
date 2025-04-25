package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.ConfigurationReader;

import java.util.List;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты DevEx")
public class DevExTests extends BaseTest{

    @Test
    @DisplayName("Тест логина")
    @Description("Описание теста")
    @Severity(CRITICAL)
    @Owner("Андрей Бровко")
    @Link(name = "Website", url = "https://www.saucedemo.com/")
    @Issue("JIRA-123")
    @TmsLink("TMS-456")
    @Epic("Авторизация")
    @Feature("Вход в систему")
    @Story("Успешный вход в систему")
    public void loginTest(){
        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);

        DashboardPage dashboardPage = loginPage.login();

        assertEquals("Dashboard", dashboardPage.getHeaderText());
    }

    @Test
    @DisplayName("Тест числа постов")
    public void postsCountTest(){
        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);

        NavigationComponent navigationComponent = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationComponent.openAllPosts();

        assertEquals("Posts", allPostsPage.getHeaderText());
        assertEquals(474, allPostsPage.getPosts().size());
    }

    @Test
    @DisplayName("Тест текстов постов")
    public void postsTextTest(){
        Allure.description("Дескрипшен текстов постов");
        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        assertEquals("Posts", allPostsPage.getHeaderText());

        PostComponent firstPost = allPostsPage.getPosts().getFirst();

        assertEquals("New test", firstPost.getPostTitle());
        assertEquals("Description", firstPost.getPostText());
    }


    @Test
    // @DisplayName("Тест фильтра постов")
    @Disabled
    public void postsFilterTest(){

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

        assertEquals("Posts", allPostsPage.getHeaderText());

        PostComponent post = allPostsPage.getPost(postComponent -> postComponent.getPostTitle().equals("JDBC"));

        assertEquals("2023/11/24", post.getPostDate());
    }

    @Test
    @DisplayName("Тест поиска постов")
    public void searchPostTest(){

        context.driver.get(ConfigurationReader.get("url") + "login");
        LoginPage loginPage = new LoginPage(context);
        NavigationComponent navigationPage = new NavigationComponent(context);

        loginPage.login();
        AllPostsPage allPostsPage = navigationPage.openAllPosts();

       allPostsPage.search("JDBC");

        List<PostComponent> searchResult = allPostsPage.getPosts();

        assertEquals(1, searchResult.size());
        assertEquals("JDBC", searchResult.getFirst().getPostTitle());
    }
}
