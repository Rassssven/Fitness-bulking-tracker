package proiect.demo.web.ang_spring.DTO.ShopDTOs;

public class ProductImageResponse {

    private Long id;
    private String fileName;
    private String url;

    public ProductImageResponse(Long id, String fileName, String url) {
        this.id = id;
        this.fileName = fileName;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }
    
}
