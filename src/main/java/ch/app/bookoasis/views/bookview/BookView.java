package ch.app.bookoasis.views.bookview;

import ch.app.bookoasis.Data.Book.Book;
import ch.app.bookoasis.Service.BookDataService;
import ch.app.bookoasis.Service.BookService;
import ch.app.bookoasis.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

import java.util.List;

@PageTitle("Books | BookOasis")
@Route(value = "book", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class BookView extends Main implements HasComponents, HasStyle {
    private OrderedList imageContainer;
    private final BookService service;
    private final BookDataService dataService;
    private List<Book> bookList;

    public BookView(BookService service, BookDataService bookDataService) {
        this.service = service;
        this.dataService = bookDataService;

        bookList = service.findAll();
        constructUI();

        for (Book book : bookList){
            BookViewCard card = new BookViewCard(book.getTitle(),
                                                 book.getPictureUrl(),
                                                 book.getTitle(),
                                                 book.getAuthor(),
                                                 book.getPublisher(),
                                                 book.getReleaseYear());
            card.addClickListener(event -> {
                dataService.setSharedData(book.getId());
                getUI().ifPresent(ui -> ui.navigate("book-detail"));
            });
            imageContainer.add(card);
        }
    }

    private void constructUI() {
        addClassNames("image-gallery-view");
        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);

        VerticalLayout headerContainer = new VerticalLayout();
        H2 header = new H2("Books");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
        Paragraph description = new Paragraph("A list of the most renown books in the world.");
        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
        headerContainer.add(header, description);

        imageContainer = new OrderedList();
        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);

        container.add(headerContainer);
        add(container, imageContainer);

    }

}
