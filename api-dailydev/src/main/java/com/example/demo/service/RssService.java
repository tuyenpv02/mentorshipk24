package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class RssService {

    public boolean scraper(String url){
        try {
            // Lấy và phân tích cú pháp HTML từ trang
            Document doc = Jsoup.connect(url).get();

            // Chọn tất cả các mục trong danh sách RSS
            Elements rssItems = doc.select("table.rssTbl tbody tr");

            // Duyệt qua từng mục và lấy title và link
            for (Element rssItem : rssItems) {
                // Lấy tên mục từ cột đầu tiên (td đầu tiên)
                String category = rssItem.select("td").first().text();

                // Lấy liên kết RSS từ cột thứ hai (td thứ hai)
                String link = rssItem.select("td a").attr("href");

                // In ra tiêu đề và liên kết đầy đủ
                System.out.println("Title: " + category);
                System.out.println("Link: https://tuoitre.vn" + link);  // Ghép URL đầy đủ
                System.out.println("-------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
