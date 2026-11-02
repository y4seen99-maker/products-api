# Products API — Tutorials 1–5

University of Westminster — 5COSC019W Object Oriented Programming.

## Tutorial 1
- Spring Boot starter application
- `/hello`, `/goodbye`, and `/status` endpoints
- Swagger/OpenAPI UI
- `Person` class with encapsulated `name` and `email`

## Tutorial 2
- `Product` class with private fields, constructors, and getters
- `Address` and `Customer` classes using an association
- `GET /customers/{id}` endpoint with nested JSON
- UML diagrams in `docs/uml/`

## Tutorial 3 — Collections and service layer
- `ProductService` manages a `List<Product>` and allocates IDs.
- `GET /products`, `GET /products/{id}`, `POST /products`, `DELETE /products/{id}`
- `GET /products/search?name=...` filters names using a stream.
- `docs/uml/product-service.puml` documents the controller/service/product relationships.

## Tutorial 4 — Inheritance
- `DigitalProduct extends Product` adds `downloadUrl` and `fileSizeMB`.
- `PhysicalProduct extends Product` adds `weightKg` and `dimensions`.
- Both types are stored in the same `List<Product>`; two sample products are seeded at startup.
- `docs/uml/product-hierarchy.puml` shows the inheritance arrows.
- The Tutorial 4 implementation originally accepted ordinary, digital and physical product JSON. Tutorial 5 changes this as described below.

## Tutorial 5 — Polymorphism and abstraction
- `Product` is now abstract and requires each subclass to override `getSummary()`.
- `DigitalProduct` and `PhysicalProduct` implement `Discountable`.
- Digital discounts use the full requested rate; physical discounts are capped at 20%.
- `GET /products/summaries` returns one summary per product using polymorphism.
- `docs/uml/product-hierarchy.puml` shows abstract Product, Discountable and their relationships.
- Following Tutorial 5's temporary POST fix, `POST /products` currently accepts a DigitalProduct JSON body only. It cannot create a plain Product (abstract) or a PhysicalProduct through this endpoint until polymorphic deserialization or separate endpoints are added.

## Main endpoints
- `GET /hello`, `GET /goodbye`, `GET /status`
- `GET /customers/{id}`
- `GET /products`
- `GET /products/summaries`
- `GET /products/{id}`
- `POST /products`
- `DELETE /products/{id}`
- `GET /products/search?name=Code`

Swagger UI: `http://localhost:8080/swagger-ui.html`

### Try POST /products

Digital product: `{"name":"Music Download","price":9.99,"downloadUrl":"/downloads/song.mp3","fileSizeMB":5.2}`

The Tutorial 5 lab deliberately changes the POST body type to DigitalProduct because Product is abstract. Existing physical seed products remain visible through GET.

### Important Tutorial 3 note
As requested in the lab, `GET /products/{id}` returns `null` for an unknown ID rather than a proper 404. That behaviour is deliberately left for the later exceptions tutorial. The catalogue is in memory and resets when the app restarts.
