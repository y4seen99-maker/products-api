# Products API — Tutorials 1–4

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
- POST accepts ordinary, digital and physical product JSON; an ID is assigned by the service.

## Main endpoints
- `GET /hello`, `GET /goodbye`, `GET /status`
- `GET /customers/{id}`
- `GET /products`
- `GET /products/{id}`
- `POST /products`
- `DELETE /products/{id}`
- `GET /products/search?name=Code`

Swagger UI: `http://localhost:8080/swagger-ui.html`

### Try POST /products
Ordinary product: `{"name":"Laptop","price":899.99}`

Digital product: `{"name":"Music Download","price":9.99,"downloadUrl":"/downloads/song.mp3","fileSizeMB":5.2}`

Physical product: `{"name":"Keyboard","price":49.99,"weightKg":0.8,"dimensions":"45cm x 15cm x 4cm"}`

### Important Tutorial 3 note
As requested in the lab, `GET /products/{id}` returns `null` for an unknown ID rather than a proper 404. That behaviour is deliberately left for the later exceptions tutorial. The catalogue is in memory and resets when the app restarts.
