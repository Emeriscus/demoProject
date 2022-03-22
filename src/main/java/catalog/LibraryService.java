package catalog;

public class LibraryService {

    private BookRepository bookRepository;
    private AudioRepository audioRepository;
    private LibraryItemRepository libraryItemRepository;

    public LibraryService(BookRepository bookRepository, AudioRepository audioRepository, LibraryItemRepository libraryItemRepository) {
        this.bookRepository = bookRepository;
        this.audioRepository = audioRepository;
        this.libraryItemRepository = libraryItemRepository;
    }

    public void addLibraryItem(LibraryItem libraryItem) {

        long id = libraryItemRepository.saveLibraryItem(libraryItem);
        if (libraryItem instanceof Book) {
            bookRepository.saveBookAndGetId(id, (Book) libraryItem);
        }
        if (libraryItem instanceof Audio) {
            audioRepository.saveAudioAndGetId(id, (Audio) libraryItem);
        }
    }

    public LibraryItem getLibraryItemByTitle(String title) {
        long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
        if (isBook(libraryItemId)) {
            return bookRepository.getBookByLibraryItemsId(libraryItemId);
        } else {
            return audioRepository.getAudioByLibraryItemsId(libraryItemId);
        }
    }

    public LibraryItem getLibraryItemById(String title) {
        long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
        if (isBook(libraryItemId)) {
            return bookRepository.getBookByLibraryItemsId(libraryItemId);
        } else {
            return audioRepository.getAudioByLibraryItemsId(libraryItemId);
        }
    }

    public void deleteLibraryItemByTitle(String title) {
        long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
        libraryItemRepository.deleteLibraryItemById(libraryItemId);
        if (isBook(libraryItemId)) {
            bookRepository.deleteBookByLibraryItemsId(libraryItemId);
        } else {
            audioRepository.deleteAudioByLibraryItemsId(libraryItemId);
        }
    }

    public void borrowLibraryItemByTitle(String title) {
        long libraryItemId = libraryItemRepository.getLibraryItemIdByTitle(title);
        libraryItemRepository.BorrowLibraryItemByTitle(libraryItemId);
    }

    private boolean isBook(long libraryItemId) {
        return libraryItemRepository.getLibraryItemTypeById(libraryItemId).equals(Book.class.toString());
    }
}
