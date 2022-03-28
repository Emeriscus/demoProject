package catalog;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    private BookRepository bookRepository;
    private AudioRepository audioRepository;
    private LibraryItemRepository libraryItemRepository;

    public LibraryService(BookRepository bookRepository, AudioRepository audioRepository, LibraryItemRepository libraryItemRepository) {
        this.bookRepository = bookRepository;
        this.audioRepository = audioRepository;
        this.libraryItemRepository = libraryItemRepository;
    }

    public long addLibraryItem(LibraryItem libraryItem) {
        long libraryItemId = libraryItemRepository.saveLibraryItem(libraryItem);
        if (libraryItem instanceof Book) {
            bookRepository.saveBookAndGetId(libraryItemId, (Book) libraryItem);
        }
        if (libraryItem instanceof Audio) {
            audioRepository.saveAudioAndGetId(libraryItemId, (Audio) libraryItem);
        }
        return libraryItemId;
    }

    public LibraryItem getLibraryItemByTitle(String title) {
        if (Validators.isBlank(title)) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        try {
            long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
            if (isBook(libraryItemId)) {
                return bookRepository.getBookByLibraryItemsId(libraryItemId);
            } else {
                return audioRepository.getAudioByLibraryItemsId(libraryItemId);
            }
        } catch (EmptyResultDataAccessException erdae) {
            throw new IllegalStateException("No result!", erdae);
        }
    }

    public LibraryItem getLibraryItemById(long libraryItemId) {
        try {
            if (isBook(libraryItemId)) {
                return bookRepository.getBookByLibraryItemsId(libraryItemId);
            } else {
                return audioRepository.getAudioByLibraryItemsId(libraryItemId);
            }
        } catch (EmptyResultDataAccessException erdae) {
            throw new IllegalStateException("No result!", erdae);
        }
    }

    public List<LibraryItem> getAllLibraryItem() {
        List<LibraryItem> result = new ArrayList<>();
        result.addAll(audioRepository.getAllAudioItem());
        result.addAll(bookRepository.getAllBookItem());
        return result;
    }

    public List<LibraryItem> getAllLibraryItemByTitleFragment(String fragment) {
        List<LibraryItem> result = new ArrayList<>();
        result.addAll(audioRepository.getAllAudioItemByTitleFragment(fragment));
        result.addAll(bookRepository.getAllBookItemByTitleFragment(fragment));
        return result;
    }

    public void deleteLibraryItemByTitle(String title) {
        if (Validators.isBlank(title)) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        try {
            long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
            if (isBook(libraryItemId)) {
                bookRepository.deleteBookByLibraryItemsId(libraryItemId);
            } else {
                audioRepository.deleteAudioByLibraryItemsId(libraryItemId);
            }
            libraryItemRepository.deleteLibraryItemById(libraryItemId);
        } catch (EmptyResultDataAccessException erdae) {
            throw new IllegalStateException("No result!", erdae);
        }
    }

    public void deleteLibraryItemById(long libraryItemId) {
        try {
            if (isBook(libraryItemId)) {
                bookRepository.deleteBookByLibraryItemsId(libraryItemId);
            } else {
                audioRepository.deleteAudioByLibraryItemsId(libraryItemId);
            }
            libraryItemRepository.deleteLibraryItemById(libraryItemId);
        } catch (EmptyResultDataAccessException erdae) {
            throw new IllegalStateException("No result!", erdae);
        }
    }

    public void borrowLibraryItemByTitle(String title) {
        if (Validators.isBlank(title)) {
            throw new IllegalArgumentException("The title cannot be empty");
        }
        try {
            long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
            libraryItemRepository.borrowLibraryItemByTitle(libraryItemId);
        } catch (EmptyResultDataAccessException erdae) {
            throw new IllegalStateException("No result!", erdae);
        }
    }

    private boolean isBook(long libraryItemId) {
        return libraryItemRepository.getLibraryItemTypeById(libraryItemId).equals(Book.class.toString());
    }
}
