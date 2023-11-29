package ch.app.bookoasis.views.bookview;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility.*;

public class BookViewCard extends ListItem {

    public BookViewCard(String title, String imageSrc, String imageAlt, String author, String publisher, int releaseYear) {
        addClassNames(Background.CONTRAST_5, Display.FLEX, FlexDirection.COLUMN, AlignItems.START, Padding.MEDIUM,
                BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(Background.CONTRAST, Display.FLEX, AlignItems.CENTER, JustifyContent.CENTER,
                Margin.Bottom.MEDIUM, Overflow.HIDDEN, BorderRadius.MEDIUM, Width.FULL);
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(imageSrc);
        image.setAlt(imageAlt);

        div.add(image);

        Span header = new Span();
        header.addClassNames(FontSize.XLARGE, FontWeight.SEMIBOLD);
        header.setText(title);

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText(author);

        Span badgeReleaseYear = new Span();
        badgeReleaseYear.getElement().setAttribute("theme", "badge");
        badgeReleaseYear.setText(String.valueOf(releaseYear));

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(badge, badgeReleaseYear);

        add(div, header, horizontalLayout);

    }
}
