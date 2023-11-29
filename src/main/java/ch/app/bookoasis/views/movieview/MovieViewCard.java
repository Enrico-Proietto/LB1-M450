package ch.app.bookoasis.views.movieview;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MovieViewCard extends ListItem {
    public MovieViewCard(String title, String imageSrc, String imageAlt) {
        addClassNames(LumoUtility.Background.CONTRAST_5, LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN, LumoUtility.AlignItems.START, LumoUtility.Padding.MEDIUM,
                LumoUtility.BorderRadius.LARGE);

        Div div = new Div();
        div.addClassNames(LumoUtility.Background.CONTRAST, LumoUtility.Display.FLEX, LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.CENTER,
                LumoUtility.Margin.Bottom.MEDIUM, LumoUtility.Overflow.HIDDEN, LumoUtility.BorderRadius.MEDIUM, LumoUtility.Width.FULL);
        div.setHeight("160px");

        Image image = new Image();
        image.setWidth("100%");
        image.setSrc(imageSrc);
        image.setAlt(imageAlt);

        div.add(image);

        Span header = new Span();
        header.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.FontWeight.SEMIBOLD);
        header.setText(title);

/*        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText(author);

        Span badgePublisher = new Span();
        badgePublisher.getElement().setAttribute("theme", "badge");
        badgePublisher.setText(publisher);

        Span badgeReleaseYear = new Span();
        badgeReleaseYear.getElement().setAttribute("theme", "badge");
        badgeReleaseYear.setText(String.valueOf(releaseYear));*/

        add(div, header);

    }
}
