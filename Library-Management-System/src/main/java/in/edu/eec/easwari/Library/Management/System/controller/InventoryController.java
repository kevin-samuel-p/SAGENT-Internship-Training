package in.edu.eec.easwari.Library.Management.System.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.edu.eec.easwari.Library.Management.System.dto.BookCreationRequest;
import in.edu.eec.easwari.Library.Management.System.dto.BookUpdationRequest;
import in.edu.eec.easwari.Library.Management.System.entity.Book;
import in.edu.eec.easwari.Library.Management.System.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /*
     * Add new book
     * POST /api/inventory/books
     */
    @PostMapping("/books")
    public Book addBook(@RequestBody BookCreationRequest request) {
        return inventoryService
                .addBook(
                    request.getTitle(), 
                    request.getAuthor(), 
                    request.getSubject());
    }

    /*
     * Update book
     * PUT /api/inventory/books/{id}
     */
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookUpdationRequest request) {
        return inventoryService
                .updateBook(
                    id, 
                    request.getTitle(), 
                    request.getAuthor(), 
                    request.getSubject());
    }

    /*
     * Delete book
     * DELETE /api/inventory/books/{id}
     */
    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        inventoryService.deleteBook(id);
        return "Book deleted successfully.";
    }

    /*
     * Mark book as damaged
     * PUT /api/inventory/books/{id}/damaged
     */
    @PutMapping("/books/{id}/damaged")
    public Book markBookAsDamaged(@PathVariable Long id) {
        return inventoryService.markDamaged(id);
    }

    /*
     * Mark book as lost
     * PUT /api/inventory/books/{id}/lost
     */
    @PutMapping("/books/{id}/lost")
    public Book markBookAsLost(@PathVariable Long id) {
        return inventoryService.markLost(id);
    }
}