package app.linktalk.backend.service;

import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class InstaService {
    private static final String USER_DATA_DIR = "playwright-session";
    private final Playwright playwright;
    private final BrowserContext context;
    private final Page page;

    public InstaService() {
        playwright = Playwright.create();

        this.context = playwright.chromium().launchPersistentContext(
                Paths.get(USER_DATA_DIR),
                new BrowserType.LaunchPersistentContextOptions().setHeadless(true)
        );
        this.page = this.context.newPage();

        page.navigate("https://www.instagram.com/");
        page.waitForSelector("input[name='username']");

        if (page.locator("input[name='username']").count() > 0) {
            System.out.println("로그인 필요! 자동 로그인 진행...");
            page.fill("input[name='username']", "_linktalk_");
            page.fill("input[name='password']", "Xovkf9898?");
            page.click("button[type='submit']");
            page.waitForTimeout(5000);
            System.out.println("로그인 성공!");
        } else {
            System.out.println("이미 로그인된 상태!");
        }
    }

    public String postComment(String postUrl, String commentText) {
        page.navigate(postUrl);
        page.waitForSelector("textarea");

        page.fill("textarea", commentText);
        page.keyboard().press("Enter");

        return "댓글 작성 완료!";
    }
}
