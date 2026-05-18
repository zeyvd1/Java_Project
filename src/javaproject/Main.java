package javaproject;

import java.awt.Desktop;
import java.net.URI;
import java.time.LocalDate;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    FlowPane productsPane = new FlowPane();
    ScrollPane scroll = new ScrollPane(productsPane);
    VBox cartItemsBox = new VBox(15);

    // ← السلة
    ShoppingCart cart = new ShoppingCart(50);

    // ================= CLOTHING OBJECTS =================
    Clothing c1 = new Clothing(
            new String[]{"S", "M", "L", "Xl"}, new String[]{"GREEN", "BLACK"},
            50.0, "Nike", 101, "T-Shirt", 299.99,
            new FlatDiscount(50), "Comfortable cotton t-shirt", 20, 4, 96);

    Clothing c2 = new Clothing(
            new String[]{"S", "M", "L"}, new String[]{"RED", "BLUE"},
            80.0, "Levi's", 102, "Jeans", 799.99,
            new PercentageDiscount(10), "Classic blue denim jeans", 15, 3, 60);

    // ================= LAPTOP OBJECTS =================
    Laptops laptop1 = new Laptops(
            20, "Dell", 1, "XPS 15", 1500,
            new FlatDiscount(10), "Intel i7 processor, 2 year warranty. Perfect for work and gaming.",
            10, 5, 170,
            new String[]{"8GB", "16GB", "32GB"},
            new String[]{"128GB", "256GB", "512GB", "1T"});

    Laptops laptop2 = new Laptops(
            25, "Apple", 2, "MacBook Pro", 2500,
            new PercentageDiscount(10), "Apple M3 processor, 2 year warranty. Best for creative professionals.",
            5, 4, 80,
            new String[]{"4GB", "8GB", "16GB"},
            new String[]{"256GB", "512GB", "1T", "2T"});

    // ================= PHONE OBJECTS =================
    Phones phone1 = new Phones(
            new String[]{"128GB", "256GB", "512GB"}, new String[]{"PINK", "BLACK"},
            10, "Samsung", 101, "Galaxy S24", 1200,
            new FlatDiscount(10), "108MP camera, 5000mAh battery, 6.7\" AMOLED display, 2 year warranty.",
            20, 4, 55);

    Phones phone2 = new Phones(
            new String[]{"64GB", "128GB", "256GB"}, new String[]{"RED", "BLUE"},
            12, "Apple", 102, "iPhone 15", 1300,
            null, "48MP camera, 3877mAh battery, 6.1\" Super Retina XDR display, 2 year warranty.",
            12, 5, 120);

    // ================= SOFTWARE LICENSE OBJECTS =================
    SoftwareLicense s1 = new SoftwareLicense(
            "ABC123-XYZ789", LocalDate.of(2027, 5, 14), 2.5,
            "https://download.com/software1", 201, "Windows Antivirus", 49.99,
            null, "Premium antivirus software", 100, 4, 110);

    SoftwareLicense s2 = new SoftwareLicense(
            "QWE456-RTY111", LocalDate.of(2026, 5, 17), 1.2,
            "https://download.com/software2", 202, "Photo Editor Pro", 79.99,
            null, "Professional photo editing software", 50, 3, 130);

    // ================= DIGITAL DOWNLOAD OBJECTS =================
    DigitalDownload d1 = new DigitalDownload(
            "MP3", 5, 120.5, "https://download.com/music_album",
            301, "Top Hits Album", 19.99,
            null, " 320kbps quality, Instant access after purchase.", 200, 4, 150);

    DigitalDownload d2 = new DigitalDownload(
            "PDF", 3, 15.2, "https://download.com/java_book",
            302, "Java Book", 29.99,
            null, "Complete Java learning ebook", 80, 5, 100);

    // =========================================================
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f5f7fa;");

        // ================= HEADER =================
        HBox header = new HBox();
        header.prefHeightProperty().bind(root.heightProperty().multiply(0.07));
        header.setMinHeight(40);
        header.maxHeightProperty().bind(root.heightProperty().multiply(0.07));

        Label title = new Label("E-Commerce Store");
        title.setStyle("""
            -fx-font-size: 32px;
            -fx-font-weight: bold;
            -fx-text-fill: #1f2937;
        """);

        header.getChildren().add(title);
        header.setAlignment(Pos.CENTER);
        header.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #dcdfe6;
            -fx-border-width: 0 0 1 0;
            -fx-padding: 25px;
        """);
        root.setTop(header);

        // Animations
        title.setOpacity(0);
        title.setTranslateY(-30);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(900), title);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        TranslateTransition slideDown = new TranslateTransition(Duration.millis(900), title);
        slideDown.setFromY(-30);
        slideDown.setToY(0);
        slideDown.setInterpolator(Interpolator.EASE_OUT);
        new ParallelTransition(fadeIn, slideDown).play();

        DropShadow glow = new DropShadow();
        glow.setColor(Color.web("#2563eb"));
        glow.setRadius(0);
        title.setEffect(glow);
        Timeline glowPulse = new Timeline(
            new KeyFrame(Duration.ZERO,
                new KeyValue(glow.radiusProperty(), 0),
                new KeyValue(glow.spreadProperty(), 0)),
            new KeyFrame(Duration.millis(1500),
                new KeyValue(glow.radiusProperty(), 15),
                new KeyValue(glow.spreadProperty(), 0.08)),
            new KeyFrame(Duration.millis(3000),
                new KeyValue(glow.radiusProperty(), 0),
                new KeyValue(glow.spreadProperty(), 0))
        );
        glowPulse.setCycleCount(Timeline.INDEFINITE);
        glowPulse.setDelay(Duration.millis(900));
        glowPulse.play();

        String[] headerStyles = {
            "-fx-background-color: white; -fx-padding: 15px; -fx-border-color: #dcdfe6; -fx-border-width: 0 0 1 0;",
            "-fx-background-color: #f0f7ff; -fx-padding: 15px; -fx-border-color: #bfdbfe; -fx-border-width: 0 0 1 0;",
            "-fx-background-color: white; -fx-padding: 15px; -fx-border-color: #dcdfe6; -fx-border-width: 0 0 1 0;",
        };
        int[] idx = {0};
        Timeline colorCycle = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            idx[0] = (idx[0] + 1) % headerStyles.length;
            header.setStyle(headerStyles[idx[0]]);
        }));
        colorCycle.setCycleCount(Timeline.INDEFINITE);
        colorCycle.play();

        // ================= LEFT SIDEBAR =================
        VBox sidebar = new VBox(25);
        sidebar.setPadding(new Insets(25));
        sidebar.prefWidthProperty().bind(root.widthProperty().multiply(0.17));
        sidebar.setMinWidth(225);
        sidebar.setStyle("""
            -fx-background-color: #e9eef5;
            -fx-border-color: #d0d7e2;
            -fx-border-width: 0 1 0 0;
        """);

        Label logo = new Label("E-Commerce\nStore");
        logo.setFont(Font.font("Arial", 28));
        logo.setStyle("-fx-font-weight: bold; -fx-text-fill: #2563eb;");

        Button homeBtn    = new Button("🏠 Home");
        Button clothesBtn = new Button("👕 Clothing");
        Button laptopsBtn = new Button("💻 Laptops");
        Button phonesBtn  = new Button("📱 Phones");
        Button digitalBtn = new Button("🎮 Digital Products");
        Button clearCartBtn = new Button("🗑 Clear Cart");

        String menuStyle = """
            -fx-background-color: transparent;
            -fx-text-fill: #1f2937;
            -fx-font-size: 16px;
            -fx-font-weight: 600;
            -fx-background-radius: 10;
            -fx-padding: 14 18 14 18;
            -fx-alignment: CENTER-LEFT;
            -fx-cursor: hand;
        """;
        String hoverStyle = """
            -fx-background-color: #dbeafe;
            -fx-text-fill: #2563eb;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-background-radius: 10;
            -fx-padding: 14 18 14 18;
            -fx-alignment: CENTER-LEFT;
            -fx-cursor: hand;
        """;
        String clearCartDefault = """
            -fx-background-color: white;
            -fx-text-fill: #111827;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-border-color: #d1d5db;
            -fx-border-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """;
        String clearCartHover = """
            -fx-background-color: #fee2e2;
            -fx-text-fill: #b91c1c;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-border-color: #fca5a5;
            -fx-border-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """;

        for (Button b : new Button[]{homeBtn, clothesBtn, laptopsBtn, phonesBtn, digitalBtn}) {
            b.setStyle(menuStyle);
            b.setMaxWidth(Double.MAX_VALUE);
            b.setOnMouseEntered(e -> b.setStyle(hoverStyle));
            b.setOnMouseExited(e -> b.setStyle(menuStyle));
        }
        clearCartBtn.setStyle(clearCartDefault);
        clearCartBtn.setMaxWidth(Double.MAX_VALUE);
        clearCartBtn.setOnMouseEntered(e -> clearCartBtn.setStyle(clearCartHover));
        clearCartBtn.setOnMouseExited(e -> clearCartBtn.setStyle(clearCartDefault));

        homeBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                createProductCard(laptop1), createProductCard(laptop2),
                createProductCard(phone1),  createProductCard(phone2),
                createProductCard(c1),      createProductCard(c2),
                createProductCard(s1),      createProductCard(s2),
                createProductCard(d1),      createProductCard(d2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
        });
        clothesBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(createProductCard(c1), createProductCard(c2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
        });
        laptopsBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(createProductCard(laptop1), createProductCard(laptop2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
        });
        phonesBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(createProductCard(phone1), createProductCard(phone2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
        });
        digitalBtn.setOnAction(e -> {
            productsPane.getChildren().setAll(
                createProductCard(s1), createProductCard(s2),
                createProductCard(d1), createProductCard(d2));
            scroll.setContent(productsPane);
            root.setCenter(scroll);
        });
        clearCartBtn.setOnAction(e -> {
            cart.clearCart();
            refreshCartUI();
        });

        sidebar.getChildren().addAll(logo, homeBtn, clothesBtn, laptopsBtn, phonesBtn, digitalBtn, clearCartBtn);
        root.setLeft(sidebar);

        // ================= RIGHT CART =================
        VBox cartPane = new VBox(20);
        cartPane.setPadding(new Insets(25));
        cartPane.prefWidthProperty().bind(root.widthProperty().multiply(0.20));
        cartPane.setMinWidth(250);
        cartPane.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #dcdfe6;
            -fx-border-width: 0 0 0 1;
        """);

        Label cartTitle = new Label("🛒 Shopping Cart");
        cartTitle.setFont(Font.font("Arial", 26));
        cartTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        cartTitle.setWrapText(true);
        cartTitle.setMinHeight(Region.USE_PREF_SIZE);

        Separator line = new Separator();
        line.setPrefHeight(2);
        line.setStyle("-fx-background-color: #e5e7eb;");

        cartItemsBox = new VBox(15);
        cartItemsBox.setAlignment(Pos.TOP_CENTER);
        cartItemsBox.setPrefHeight(500);
        cartItemsBox.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #e5e7eb;
            -fx-border-radius: 10;
            -fx-background-radius: 10;
            -fx-padding: 15;
        """);

        // سلة فارغة في البداية
        refreshCartUI();

        Button checkoutBtn = new Button("Checkout");
        checkoutBtn.setMaxWidth(Double.MAX_VALUE);
        checkoutBtn.setStyle("""
            -fx-background-color: #2fb344;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
        """);
        checkoutBtn.setOnMouseEntered(e -> checkoutBtn.setStyle("""
            -fx-background-color: #24963a;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
            -fx-cursor: hand;
        """));
        checkoutBtn.setOnMouseExited(e -> checkoutBtn.setStyle("""
            -fx-background-color: #2fb344;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 14;
        """));

        cartPane.getChildren().addAll(cartTitle, line, cartItemsBox, checkoutBtn);
        root.setRight(cartPane);

        // ================= CENTER PRODUCTS =================
        productsPane.setPadding(new Insets(20));
        productsPane.setHgap(20);
        productsPane.setVgap(20);
        productsPane.setAlignment(Pos.TOP_CENTER);
        productsPane.setStyle("-fx-background-color: #fff;");

        productsPane.getChildren().addAll(
            createProductCard(c1),      createProductCard(c2),
            createProductCard(laptop1), createProductCard(laptop2),
            createProductCard(phone1),  createProductCard(phone2),
            createProductCard(s1),      createProductCard(s2),
            createProductCard(d1),      createProductCard(d2));

        scroll = new ScrollPane(productsPane);
        scroll.setFitToWidth(true);
        scroll.setStyle("""
            -fx-background: #f5f7fa;
            -fx-background-color: #f5f7fa;
            -fx-border-color: transparent;
        """);
        scroll.prefWidthProperty().bind(
            root.widthProperty()
                .subtract(sidebar.widthProperty())
                .subtract(cartPane.widthProperty()));
        root.setCenter(scroll);

        Scene scene = new Scene(root);
        stage.setTitle("E-Commerce Product Store");
        stage.setMinHeight(645);
        stage.setMinWidth(730);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // =========================================================
    //  refreshCartUI — تحدّث عرض السلة
    // =========================================================
    private void refreshCartUI() {
        cartItemsBox.getChildren().clear();

        if (cart.isEmpty()) {
            Label emptyIcon = new Label("🛒");
            emptyIcon.setFont(Font.font(60));
            emptyIcon.setTextFill(Color.web("#9ca3af"));

            Label emptyMsg = new Label("Your cart is empty");
            emptyMsg.setFont(Font.font(18));
            emptyMsg.setTextFill(Color.web("#6b7280"));

            cartItemsBox.getChildren().addAll(emptyIcon, emptyMsg);
            return;
        }

        double grandTotal = 0;

        for (int i = 0; i < cart.getSize(); i++) {
            CartItem item = cart.getItems()[i];
            Product  prod = item.getProduct();
            String   tag  = prod.getDisplayTag();

            // ── بطاقة العنصر ──
            VBox itemCard = new VBox(5);
            itemCard.setPadding(new Insets(10));
            itemCard.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-background-radius: 8;
                -fx-border-color: #e5e7eb;
                -fx-border-radius: 8;
            """);

            // اسم المنتج
            Label nameL = new Label(prod.name);
            nameL.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
            nameL.setWrapText(true);
            itemCard.getChildren().add(nameL);

            // تاج اللون/الـ size
            if (tag != null && !tag.isEmpty()) {
                Label tagL = new Label(tag);
                tagL.setStyle("""
                    -fx-background-color: #dbeafe;
                    -fx-text-fill: #1d4ed8;
                    -fx-font-size: 11px;
                    -fx-padding: 2 6 2 6;
                    -fx-background-radius: 4;
                """);
                itemCard.getChildren().add(tagL);
            }

            // السعر
            Label priceL = new Label(String.format("$%.2f / unit", prod.getFinalPrice()));
            priceL.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
            itemCard.getChildren().add(priceL);

            // ── صف الكمية ──
            HBox qtyRow = new HBox(6);
            qtyRow.setAlignment(Pos.CENTER_LEFT);

            Button minusBtn = new Button("−");
            Button plusBtn  = new Button("+");
            String qtyBtnStyle = """
                -fx-background-color: #e5e7eb;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 6;
                -fx-min-width: 26;
                -fx-min-height: 26;
                -fx-cursor: hand;
            """;
            minusBtn.setStyle(qtyBtnStyle);
            plusBtn.setStyle(qtyBtnStyle);

            Label qtyLabel = new Label(String.valueOf(item.getQuantity()));
            qtyLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-min-width: 22; -fx-alignment: CENTER;");

            Label subTotalL = new Label(String.format("$%.2f", item.getSubTotal()));
            subTotalL.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #2fb344;");

            Button removeBtn = new Button("🗑");
            removeBtn.setStyle("-fx-background-color: transparent; -fx-font-size: 13px; -fx-cursor: hand;");

            final int productId = prod.id;

            minusBtn.setOnAction(ev -> {
                if (item.getQuantity() > 1) item.decreaseQuantity();
                else cart.removeProduct(productId);
                refreshCartUI();
            });
            plusBtn.setOnAction(ev -> {
                item.increaseQuantity();
                refreshCartUI();
            });
            removeBtn.setOnAction(ev -> {
                cart.removeProduct(productId);
                refreshCartUI();
            });

            qtyRow.getChildren().addAll(minusBtn, qtyLabel, plusBtn, subTotalL, removeBtn);
            itemCard.getChildren().add(qtyRow);
            cartItemsBox.getChildren().add(itemCard);

            grandTotal += item.getSubTotal();
        }

        // الإجمالي
        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #e5e7eb;");

        HBox totalRow = new HBox();
        totalRow.setAlignment(Pos.CENTER_RIGHT);
        Label totalL = new Label(String.format("Total:  $%.2f", grandTotal));
        totalL.setFont(Font.font("Arial", 16));
        totalL.setStyle("-fx-font-weight: bold; -fx-text-fill: #111827;");
        totalRow.getChildren().add(totalL);

        cartItemsBox.getChildren().addAll(sep, totalRow);
    }

    // =========================================================
    //  getProductImageUrl
    // =========================================================
    private String getProductImageUrl(Product p) {
        if (p instanceof Laptops) {
            if (p.name.toLowerCase().contains("mac"))
                return "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1593642632559-0c6d3fc62b89?w=200&h=140&fit=crop";
        } else if (p instanceof Phones) {
            if (p.name.toLowerCase().contains("iphone"))
                return "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=200&h=140&fit=crop";
        } else if (p instanceof Clothing) {
            if (p.name.toLowerCase().contains("jeans"))
                return "https://images.unsplash.com/photo-1542272604-787c3835535d?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=200&h=140&fit=crop";
        } else if (p instanceof SoftwareLicense) {
            if (p.name.toLowerCase().contains("photo"))
                return "https://images.unsplash.com/photo-1611532736597-de2d4265fba3?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=200&h=140&fit=crop";
        } else if (p instanceof DigitalDownload) {
            if (p.name.toLowerCase().contains("music") || p.name.toLowerCase().contains("album"))
                return "https://images.unsplash.com/photo-1470225620780-dba8ba36b745?w=200&h=140&fit=crop";
            return "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=200&h=140&fit=crop";
        }
        return "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=200&h=140&fit=crop";
    }

    // =========================================================
    //  createProductCard
    // =========================================================
    private VBox createProductCard(Product p) {

        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setPrefWidth(180);
        card.setAlignment(Pos.CENTER);
        card.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-border-color: #e5e7eb;
            -fx-border-radius: 12;
            -fx-padding: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);
            -fx-cursor: hand;
        """);

        card.setOnMouseEntered(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
            tt.setToY(-5);
            tt.play();
        });
        card.setOnMouseExited(e -> {
            TranslateTransition tt = new TranslateTransition(Duration.millis(150), card);
            tt.setToY(0);
            tt.play();
        });

        // صورة المنتج
        ImageView imageView = new ImageView();
        imageView.setFitWidth(170);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(false);
        try {
            imageView.setImage(new Image(getProductImageUrl(p), 200, 140, false, true, true));
        } catch (Exception ex) { }

        Label nameLabel  = new Label(p.name);
        nameLabel.setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label priceLabel = new Label(String.format("$%.2f", p.getFinalPrice()));
        priceLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        // ── زرار Add to Cart ──
        Button addBtn = new Button("🛒 Add to Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        String addBtnDefault = """
            -fx-background-color: #edf2f7;
            -fx-text-fill: #2563eb;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
            -fx-cursor: hand;
        """;
        String addBtnHover = """
            -fx-background-color: #dbeafe;
            -fx-text-fill: #1d4ed8;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
            -fx-cursor: hand;
        """;
        addBtn.setStyle(addBtnDefault);
        addBtn.setOnMouseEntered(e -> addBtn.setStyle(addBtnHover));
        addBtn.setOnMouseExited(e -> addBtn.setStyle(addBtnDefault));

        // ── لما يضغط Add to Cart ──
        addBtn.setOnAction(ev -> {
            ev.consume(); // منع الحدث يوصل للـ card

            // المنتجات اللي عندها خيارات: بتفتح نافذة اختيار
            if (p instanceof Clothing || p instanceof Phones || p instanceof Laptops) {
                showOptionsWindow(p, addBtn);
            } else {
                // Software / Digital: يضيف مباشرة بدون خيارات
                p.setDisplayTag("");
                cart.addProduct(p, 1);
                refreshCartUI();
                flashAddBtn(addBtn, addBtnDefault);
            }
        });

        // ── نافذة التفاصيل لما يضغط على الكارت نفسه ──
        card.setOnMouseClicked(e -> showDetailWindow(p));

        card.getChildren().addAll(imageView, nameLabel, priceLabel, addBtn);
        return card;
    }

    // =========================================================
    //  showOptionsWindow — نافذة اختيار اللون والـ Size/Storage/RAM
    // =========================================================
    private void showOptionsWindow(Product p, Button addBtn) {

        Stage optStage = new Stage();
        optStage.setTitle("Choose Options — " + p.name);

        VBox box = new VBox(18);
        box.setPadding(new Insets(28));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-background-color: #f9fafb;");

        Label heading = new Label("Choose options for:");
        heading.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");

        Label pName = new Label(p.name);
        pName.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        box.getChildren().addAll(heading, pName, new Separator());

        // ── متغيرات الاختيار ──
        String[] selectedColor = {""};
        String[] selectedOpt   = {""};

        // ════ اللون (Clothing & Phones) ════
        if (p instanceof Clothing || p instanceof Phones) {
            String[] colors = (p instanceof Clothing)
                    ? ((Clothing) p).colors
                    : ((Phones)   p).colors;

            selectedColor[0] = colors[0];

            Label colorLbl = new Label("Color");
            colorLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #374151;");

            HBox colorRow = new HBox(12);
            colorRow.setAlignment(Pos.CENTER_LEFT);

            Circle[] circles = new Circle[colors.length];
            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(13);
                try { c.setFill(Color.web(colors[i])); }
                catch (Exception ex) { c.setFill(Color.LIGHTGRAY); }
                c.setStroke(i == 0 ? Color.web("#2563eb") : Color.web("#d1d5db"));
                c.setStrokeWidth(i == 0 ? 3 : 1.5);
                c.setStyle("-fx-cursor: hand;");
                circles[i] = c;
                final int fi = i;
                c.setOnMouseClicked(ev -> {
                    selectedColor[0] = colors[fi];
                    for (Circle cr : circles) { cr.setStroke(Color.web("#d1d5db")); cr.setStrokeWidth(1.5); }
                    c.setStroke(Color.web("#2563eb")); c.setStrokeWidth(3);
                });
                colorRow.getChildren().add(c);
            }
            box.getChildren().addAll(colorLbl, colorRow);
        }

        // ════ الـ Size (Clothing) ════
        if (p instanceof Clothing) {
            String[] sizes = ((Clothing) p).sizes;
            selectedOpt[0] = sizes[0];

            Label sizeLbl = new Label("Size");
            sizeLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #374151;");

            HBox sizeRow = new HBox(8);
            sizeRow.setAlignment(Pos.CENTER_LEFT);
            Button[] btns = buildOptionButtons(sizes, selectedOpt);
            sizeRow.getChildren().addAll(btns);
            box.getChildren().addAll(sizeLbl, sizeRow);
        }

        // ════ الـ Storage (Phones) ════
        if (p instanceof Phones) {
            String[] storages = ((Phones) p).storages;
            selectedOpt[0] = "Storage: " + storages[0];

            Label storLbl = new Label("Storage");
            storLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #374151;");

            HBox storRow = new HBox(8);
            storRow.setAlignment(Pos.CENTER_LEFT);
            // نبني الأزرار يدوياً عشان نحدّث selectedOpt بالـ prefix
            Button[] btns = new Button[storages.length];
            for (int i = 0; i < storages.length; i++) {
                Button b = new Button(storages[i]);
                btns[i] = b;
                final int fi = i;
                b.setStyle(i == 0 ? optBtnActive() : optBtnNormal());
                b.setOnAction(ae -> {
                    selectedOpt[0] = "Storage: " + storages[fi];
                    for (Button x : btns) x.setStyle(optBtnNormal());
                    b.setStyle(optBtnActive());
                });
                storRow.getChildren().add(b);
            }
            box.getChildren().addAll(storLbl, storRow);
        }

        // ════ الـ RAM + Storage (Laptops) ════
        if (p instanceof Laptops) {
            String[] rams     = ((Laptops) p).rams;
            String[] storages = ((Laptops) p).storages;
            String[] selRam   = {rams[0]};
            String[] selStor  = {storages[0]};
            selectedOpt[0] = "RAM: " + rams[0] + " | " + storages[0];

            Label ramLbl = new Label("RAM");
            ramLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #374151;");
            HBox ramRow = new HBox(8);
            ramRow.setAlignment(Pos.CENTER_LEFT);
            Button[] ramBtns = new Button[rams.length];
            for (int i = 0; i < rams.length; i++) {
                Button b = new Button(rams[i]);
                ramBtns[i] = b;
                final int fi = i;
                b.setStyle(i == 0 ? optBtnActive() : optBtnNormal());
                b.setOnAction(ae -> {
                    selRam[0] = rams[fi];
                    selectedOpt[0] = "RAM: " + selRam[0] + " | " + selStor[0];
                    for (Button x : ramBtns) x.setStyle(optBtnNormal());
                    b.setStyle(optBtnActive());
                });
                ramRow.getChildren().add(b);
            }

            Label storLbl = new Label("Storage");
            storLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #374151;");
            HBox storRow = new HBox(8);
            storRow.setAlignment(Pos.CENTER_LEFT);
            Button[] storBtns = new Button[storages.length];
            for (int i = 0; i < storages.length; i++) {
                Button b = new Button(storages[i]);
                storBtns[i] = b;
                final int fi = i;
                b.setStyle(i == 0 ? optBtnActive() : optBtnNormal());
                b.setOnAction(ae -> {
                    selStor[0] = storages[fi];
                    selectedOpt[0] = "RAM: " + selRam[0] + " | " + selStor[0];
                    for (Button x : storBtns) x.setStyle(optBtnNormal());
                    b.setStyle(optBtnActive());
                });
                storRow.getChildren().add(b);
            }
            box.getChildren().addAll(ramLbl, ramRow, storLbl, storRow);
        }

        // ── زرار التأكيد ──
        box.getChildren().add(new Separator());
        Button confirmBtn = new Button("✅  Add to Cart");
        confirmBtn.setPrefWidth(240);
        confirmBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """);
        confirmBtn.setOnMouseEntered(e -> confirmBtn.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """));
        confirmBtn.setOnMouseExited(e -> confirmBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 12;
            -fx-cursor: hand;
        """));

        confirmBtn.setOnAction(ce -> {
            // بناء الـ tag النهائي
            StringBuilder tag = new StringBuilder();
            if (!selectedColor[0].isEmpty()) tag.append("Color: ").append(selectedColor[0]);
            if (!selectedOpt[0].isEmpty()) {
                if (tag.length() > 0) tag.append("  |  ");
                tag.append(selectedOpt[0]);
            }
            p.setDisplayTag(tag.toString());
            cart.addProduct(p, 1);
            refreshCartUI();
            flashAddBtn(addBtn, """
                -fx-background-color: #edf2f7;
                -fx-text-fill: #2563eb;
                -fx-font-size: 15px;
                -fx-font-weight: bold;
                -fx-background-radius: 8;
                -fx-padding: 10 18 10 18;
                -fx-cursor: hand;
            """);
            optStage.close();
        });

        box.getChildren().add(confirmBtn);

        Scene sc = new Scene(box);
        optStage.setScene(sc);
        optStage.setResizable(false);
        optStage.show();
    }

    // ── مساعد: بناء أزرار خيارات ──
    private Button[] buildOptionButtons(String[] options, String[] selectedRef) {
        Button[] btns = new Button[options.length];
        for (int i = 0; i < options.length; i++) {
            Button b = new Button(options[i]);
            btns[i] = b;
            final int fi = i;
            b.setStyle(i == 0 ? optBtnActive() : optBtnNormal());
            b.setOnAction(ae -> {
                selectedRef[0] = options[fi];
                for (Button x : btns) x.setStyle(optBtnNormal());
                b.setStyle(optBtnActive());
            });
        }
        return btns;
    }

    private String optBtnNormal() {
        return "-fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 5 14 5 14; -fx-background-color: #e5e7eb; -fx-text-fill: #374151;";
    }
    private String optBtnActive() {
        return "-fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 6; -fx-padding: 5 14 5 14; -fx-background-color: #2563eb; -fx-text-fill: white;";
    }

    // ── فلاش أخضر على زرار Add to Cart ──
    private void flashAddBtn(Button btn, String defaultStyle) {
        btn.setText("✓ Added!");
        btn.setStyle("""
            -fx-background-color: #dcfce7;
            -fx-text-fill: #16a34a;
            -fx-font-size: 15px;
            -fx-font-weight: bold;
            -fx-background-radius: 8;
            -fx-padding: 10 18 10 18;
        """);
        new Timeline(new KeyFrame(Duration.millis(1400), ae -> {
            btn.setText("🛒 Add to Cart");
            btn.setStyle(defaultStyle);
        })).play();
    }

    // =========================================================
    //  showDetailWindow — نافذة التفاصيل (كما كانت بالضبط)
    // =========================================================
    private void showDetailWindow(Product p) {

        Stage detailStage = new Stage();
        detailStage.setTitle(p.name + " - Details");

        VBox content = new VBox(20);
        content.setPadding(new Insets(40));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #f5f7fa;");

        ImageView imageViewIn = new ImageView();
        imageViewIn.setFitWidth(420);
        imageViewIn.setFitHeight(300);
        imageViewIn.setPreserveRatio(false);
        try {
            imageViewIn.setImage(new Image(getProductImageUrl(p), 200, 140, false, true, true));
        } catch (Exception ex) { }
        content.getChildren().add(imageViewIn);

        Label detailName = new Label(p.name);
        detailName.setFont(Font.font("Arial", 28));
        detailName.setStyle("-fx-font-weight: bold; -fx-text-fill: #111827;");
        detailName.setMaxWidth(320);
        detailName.setTextAlignment(TextAlignment.LEFT);

        HBox rateBox = new HBox(10);
        HBox stars = new HBox(3);
        for (int i = 1; i <= 5; i++) {
            Label star = new Label("⭐");
            star.setStyle("-fx-font-size: 30px; -fx-padding: -15 0 -25 0;");
            star.setTextFill(Color.web("#FFAD33"));
            if (i > p.rating) star.setOpacity(0.6);
            stars.getChildren().add(star);
        }
        Label reviews = new Label("(" + p.reviews + " reviews)");
        reviews.setFont(Font.font(16));
        reviews.setTextFill(Color.web("#aaa"));
        rateBox.getChildren().addAll(stars, reviews);
        rateBox.setAlignment(Pos.CENTER_LEFT);

        Label detailDesc = new Label(p.description);
        detailDesc.setTextFill(Color.web("#6b7280"));
        detailDesc.setFont(Font.font(15));
        detailDesc.setWrapText(true);
        detailDesc.setMaxWidth(320);
        detailDesc.setTextAlignment(TextAlignment.LEFT);

        HBox HBoxPrice = new HBox(20);
        Label detailPrice = new Label(String.format("$%.2f", p.getFinalPrice()));
        detailPrice.setFont(Font.font("Arial", 32));
        detailPrice.setTextFill(Color.web("#2fb344"));
        detailPrice.setMaxWidth(320);

        double tax      = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).calculateTax()       : 0;
        double shipping = (p instanceof PhysicalProduct) ? ((PhysicalProduct) p).getShippableCost() : 0;
        Text oldPrice = new Text("");
        if (p.discount != null) {
            oldPrice.setText(String.format("$%.2f", p.price + tax + shipping));
            oldPrice.setFont(Font.font("Arial", 30));
            oldPrice.setStrikethrough(true);
            oldPrice.setFill(Color.web("#999"));
        }
        HBoxPrice.getChildren().addAll(detailPrice, oldPrice);

        Button buyBtn = new Button("🛒  Add to Cart");
        buyBtn.setPrefWidth(320);
        buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white;
            -fx-font-size: 16px;
            -fx-font-weight: bold;
            -fx-padding: 14;
            -fx-background-radius: 8;
        """);
        buyBtn.setOnMouseEntered(ev -> buyBtn.setStyle("""
            -fx-background-color: #1d4ed8;
            -fx-text-fill: white; -fx-font-size: 16px;
            -fx-font-weight: bold; -fx-padding: 14;
            -fx-background-radius: 8; -fx-cursor: hand;
        """));
        buyBtn.setOnMouseExited(ev -> buyBtn.setStyle("""
            -fx-background-color: #2563eb;
            -fx-text-fill: white; -fx-font-size: 16px;
            -fx-font-weight: bold; -fx-padding: 14;
            -fx-background-radius: 8;
        """));
        buyBtn.setOnAction(ev -> {
            detailStage.close();
            if (p instanceof Clothing || p instanceof Phones || p instanceof Laptops) {
                showOptionsWindow(p, buyBtn);
            } else {
                p.setDisplayTag("");
                cart.addProduct(p, 1);
                refreshCartUI();
            }
        });

        content.getChildren().addAll(detailName, detailDesc, rateBox);

        // Clothing options in detail window
        if (p instanceof Clothing) {
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);
            String[] colors  = ((Clothing) p).colors;
            Circle[] circles = new Circle[colors.length];
            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                c.setFill(Color.web(colors[i]));
                circles[i] = c;
                int index = i;
                c.setOnMouseEntered(event -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(event -> {
                    for (Circle cr : circles) { cr.setStroke(Color.TRANSPARENT); }
                    c.setStroke(Color.web("#999")); c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(colorBox);

            HBox sizeBox = new HBox(10);
            Label sizeLabel = new Label("Size : ");
            sizeLabel.setFont(Font.font("Arial", 22));
            sizeLabel.setTextFill(Color.web("#555"));
            String[] clothingSizes = ((Clothing) p).sizes;
            String[] selectedSize  = {clothingSizes[0]};
            sizeBox.getChildren().add(sizeLabel);
            for (String size : clothingSizes) {
                Button sizeBtn = new Button(size);
                sizeBtn.setStyle(size.equals(clothingSizes[0]) ? optBtnActive() : "-fx-font-weight: bold;");
                sizeBtn.setOnMouseClicked(event -> {
                    selectedSize[0] = size;
                    for (var node : sizeBox.getChildren()) {
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    }
                    sizeBtn.setStyle(optBtnActive());
                });
                sizeBox.getChildren().add(sizeBtn);
            }
            content.getChildren().add(sizeBox);
        }

        // Phones options in detail window
        if (p instanceof Phones) {
            HBox colorBox = new HBox(15);
            Label colorLabel = new Label("Color : ");
            colorLabel.setFont(Font.font("Arial", 22));
            colorLabel.setTextFill(Color.web("#555"));
            colorBox.getChildren().add(colorLabel);
            String[] colors  = ((Phones) p).colors;
            Circle[] circles = new Circle[colors.length];
            for (int i = 0; i < colors.length; i++) {
                Circle c = new Circle(8);
                c.setFill(Color.web(colors[i]));
                circles[i] = c;
                c.setOnMouseEntered(event -> c.setStyle("-fx-cursor: hand;"));
                c.setOnMouseClicked(event -> {
                    for (Circle cr : circles) { cr.setStroke(Color.TRANSPARENT); }
                    c.setStroke(Color.web("#999")); c.setStrokeWidth(2);
                });
                colorBox.getChildren().add(c);
            }
            colorBox.setAlignment(Pos.CENTER_LEFT);
            content.getChildren().add(colorBox);

            HBox storageBox = new HBox(10);
            Label storageLabel = new Label("Storage : ");
            storageLabel.setFont(Font.font("Arial", 22));
            storageLabel.setTextFill(Color.web("#555"));
            String[] phoneStorages  = ((Phones) p).storages;
            String[] selectedStorage = {phoneStorages[0]};
            storageBox.getChildren().add(storageLabel);
            for (String storage : phoneStorages) {
                Button storageBtn = new Button(storage);
                storageBtn.setStyle(storage.equals(phoneStorages[0]) ? optBtnActive() : "-fx-font-weight: bold;");
                storageBtn.setOnMouseClicked(event -> {
                    selectedStorage[0] = storage;
                    for (var node : storageBox.getChildren()) {
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    }
                    storageBtn.setStyle(optBtnActive());
                });
                storageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add(storageBox);
        }

        // Laptops options in detail window
        if (p instanceof Laptops) {
            HBox laptopStorageBox = new HBox(10);
            Label laptopStorageLabel = new Label("Storage : ");
            laptopStorageLabel.setFont(Font.font("Arial", 22));
            laptopStorageLabel.setTextFill(Color.web("#555"));
            String[] laptopStorages      = ((Laptops) p).storages;
            String[] selectedLaptopStorage = {laptopStorages[0]};
            laptopStorageBox.getChildren().add(laptopStorageLabel);
            for (String storage : laptopStorages) {
                Button storageBtn = new Button(storage);
                storageBtn.setStyle(storage.equals(laptopStorages[0]) ? optBtnActive() : "-fx-font-weight: bold;");
                storageBtn.setOnMouseClicked(event -> {
                    selectedLaptopStorage[0] = storage;
                    for (var node : laptopStorageBox.getChildren()) {
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    }
                    storageBtn.setStyle(optBtnActive());
                });
                laptopStorageBox.getChildren().add(storageBtn);
            }
            content.getChildren().add(laptopStorageBox);

            HBox ramBox = new HBox(10);
            Label ramLabel = new Label("RAM : ");
            ramLabel.setFont(Font.font("Arial", 22));
            ramLabel.setTextFill(Color.web("#555"));
            String[] rams      = ((Laptops) p).rams;
            String[] selectedRam = {rams[0]};
            ramBox.getChildren().add(ramLabel);
            for (String ram : rams) {
                Button ramBtn = new Button(ram);
                ramBtn.setStyle(ram.equals(rams[0]) ? optBtnActive() : "-fx-font-weight: bold;");
                ramBtn.setOnMouseClicked(event -> {
                    selectedRam[0] = ram;
                    for (var node : ramBox.getChildren()) {
                        if (node instanceof Button btn) btn.setStyle("-fx-font-weight: bold;");
                    }
                    ramBtn.setStyle(optBtnActive());
                });
                ramBox.getChildren().add(ramBtn);
            }
            content.getChildren().add(ramBox);
        }

        // Digital products
        if (p instanceof DigitalProduct) {
            DigitalProduct dp = (DigitalProduct) p;
            Label linkLabel = new Label("Download Link: " + dp.downloadLink);
            linkLabel.setTextFill(Color.web("#00c6ff"));
            linkLabel.setStyle("-fx-cursor: hand;");
            linkLabel.setOnMouseClicked(ev -> {
                try {
                    if (dp instanceof DigitalDownload dd) {
                        if (dd.downloadLimit > 0) {
                            dd.download();
                            Desktop.getDesktop().browse(new URI(dp.downloadLink));
                        }
                    } else {
                        Desktop.getDesktop().browse(new URI(dp.downloadLink));
                    }
                } catch (Exception ex) { ex.printStackTrace(); }
            });
            Label fileSizeLabel = new Label("File Size: " + dp.fileSize + " MB");
            fileSizeLabel.setTextFill(Color.web("#555"));
            fileSizeLabel.setFont(Font.font("Arial", 15));
            content.getChildren().addAll(linkLabel, fileSizeLabel);

            if (p instanceof DigitalDownload dd) {
                content.getChildren().add(new Label("Format: " + dd.format));
                content.getChildren().add(new Label("Downloads remaining: " + dd.downloadLimit));
            }

            if (p instanceof SoftwareLicense sl) {
                Label licenseKeyLabel = new Label("License Key: " + sl.licenseKey);
                licenseKeyLabel.setTextFill(Color.web("#555"));
                licenseKeyLabel.setFont(Font.font("Arial", 15));

                Label expiryLabel = new Label("Expires: " + sl.expiryDate);
                expiryLabel.setTextFill(Color.web("#555"));
                expiryLabel.setFont(Font.font("Arial", 15));

                Button activateBtn = new Button("Activate License");
                activateBtn.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 8;");
                boolean[] activated = {false};
                activateBtn.setOnMouseClicked(ev -> {
                    if (sl.activateLicense()) {
                        activated[0] = true;
                        activateBtn.setText("✓ Activated");
                        activateBtn.setStyle("-fx-background-color: #0d9488; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
                    } else {
                        activated[0] = true;
                        activateBtn.setText("✗ License Expired");
                        activateBtn.setStyle("-fx-background-color: #e53e3e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
                    }
                });
                HBox expiryBox = new HBox(15, expiryLabel, activateBtn);
                expiryBox.setAlignment(Pos.CENTER_LEFT);
                content.getChildren().addAll(licenseKeyLabel, expiryBox);
            }
        }

        content.getChildren().addAll(HBoxPrice, buyBtn);

        Scene detailScene = new Scene(content, 420, 750);
        detailStage.setScene(detailScene);
        detailStage.setResizable(false);
        detailStage.show();
    }
}
