# Tinybeans Evaluation

My implementation of the Tinybeans Evaluation. 

Running the app is as per the original instructions, i.e:
```
docker-compose up --build
```

And the front end react app can be accessed at `http://localhost:3000`

You can access two additional REST endpoints to view data:
`http://localhost:8080/api/v1/products` - for viewing products

And

`http://localhost:8080/api/v1/orders` - for viewing orders

I believe I've fulfilled the original requirements:
1. As a customer I want to see a list of products.
2. As a customer I want to select a product to purchase.
3. As a customer I want to pay for the product with my credit card.
4. As a customer I want to see confirmation that I’ve paid for the product.

As well, you can add multiple products to a cart, empty the cart and view a running total of the cart total cost. React.js is not my forte but I've done my best to make it look
reasonable and function as expected.


### Product Changes 

I changed the entity classes slightly. I added a `product` entity and modified the `item` entity so that it has a relationship to products. 
An order item now contains a reference to the product, a quantity and a price (the price the user paid as opposed to the `product` price since the product price may change over time)

The original code had a circular reference so when viewing orders via the above orders endpoint we ended up with an unwieldy dataset.

In building other e-commerce applications I have always used 2 separate entities: products and order items with a reference to the product in the order item.

NOTE: I did try to use flyway to populate the database but was unsuccessful in getting it running (I didn't want to spend to much time on this instead of using the easier hibernate 
default of 'blah2.sql' on the classpath) 

->When the user clicks on a product it should take him to the checkout screen
I got a bit into the project and rather than just going straight to the checkout screen I created a cart implementation.

### Checkout screen

I used Stripe as my payment platform.
I added name, email and address to this screen in order to make it work with stripe.
There is some basic form validation on the checkout (the user has to fill in all fields and if there is an issue - for example if you don't put in a 16 char cc number), there will 
be an indication on the screen for this. As well the pay button is disabled until all form fields are filled out.

### Success screen

The success screen is the same as the original image.

### Tests
There are two very basic test cases in the test folder to test hitting the controllers and loading of the product data.
They are simply run using the intelliJ's point and click tools. 



