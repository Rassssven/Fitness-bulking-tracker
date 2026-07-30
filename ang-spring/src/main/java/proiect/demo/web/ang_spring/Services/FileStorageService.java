package proiect.demo.web.ang_spring.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	@Value("${file.upload-dir}")
	private String dir;
	
	public String saveFile(MultipartFile file) {
		
		try {
			Path uploadPath = Paths.get(dir);
			
			if(Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			String extension = "";
			String original = file.getOriginalFilename();
			
			if(original != null && original.contains(".")) {
				extension = original.substring(original.lastIndexOf("."));
			}
			
			String fileName = UUID.randomUUID() + extension;
            Path target = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
			
		} catch (IOException e) {
			throw new RuntimeException("Failed to store file", e);
		}
	}

		
		
}
