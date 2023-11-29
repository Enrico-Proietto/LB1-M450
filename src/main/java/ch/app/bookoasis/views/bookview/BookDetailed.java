package ch.app.bookoasis.views.bookview;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Data.Role.Role;
import ch.app.bookoasis.Service.BookDataService;
import ch.app.bookoasis.Service.BookService;
import ch.app.bookoasis.views.MainLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "book-detail", layout = MainLayout.class)
@PageTitle("Book Info | BookOasis")
@RolesAllowed({"USER", "ADMIN"})
public class BookDetailed extends VerticalLayout {
    private final BookService service;
    private final BookDataService dataService;

    public BookDetailed(BookService service, BookDataService bookDataService){
        this.service = service;
        this.dataService = bookDataService;

        Long id = dataService.getSharedData();
        Book book = service.findBookById(id);

        Image image = new Image();
        image.setSrc(book.getPictureUrl());
        image.setHeight("600px");
        image.setWidth("375px");
        image.getStyle().set("margin-top","100px");

        TextField titleField = new TextField();
        titleField.setValue(book.getTitle());
        titleField.setWidth("400px");
        titleField.getStyle().set("font-size", "35px");
        titleField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        titleField.getStyle().set("margin-left","130px");
        titleField.setReadOnly(true);

        TextArea information = new TextArea();
        information.setValue("Author: " + book.getAuthor() + "\n" +
                             "Publisher: " + book.getPublisher() + "\n" +
                             "Release Year: " + book.getReleaseYear() + "\n" +
                             "Pages: " + book.getPages() + "\n" +
                             "ISBN: " + book.getIsbn() + "\n" +
                             "In stock: " + book.getInStock() + "\n" +
                             "Borrowed: " + book.getBorrowed() + "\n");
        information.setWidth("670px");
        information.setHeight("390px");
        information.getStyle().set("font-size","35px");
        information.setReadOnly(true);

        TextArea description = new TextArea();
        description.setValue(book.getDescription());
        description.setWidth("670px");
        description.setHeight("500px");
        description.getStyle().set("font-size","28px");
        description.setReadOnly(true);

        VerticalLayout verticalLayout = new VerticalLayout(titleField, information, description);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(image, verticalLayout);
        horizontalLayout.getStyle().set("margin-left","600px");

        add(horizontalLayout);
    }
}
