package at.davl.main.controller;

import at.davl.main.entities.Folder;
import at.davl.main.repository.ContentRepository;
import at.davl.main.repository.FolderRepository;
import at.davl.main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/content/")
public class ContentController {

    private final ContentRepository contentRepository;
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;



    public ContentController(ContentRepository contentRepository, FolderRepository folderRepository, UserRepository userRepository) {
        this.contentRepository = contentRepository;
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public Iterable<Folder> findAll() {
        return folderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Folder findById(@PathVariable("id") Folder folder) {
        return folder;
    }

}
