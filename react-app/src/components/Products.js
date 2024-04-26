import React, {useContext, useEffect, useState} from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Card from "react-bootstrap/Card";
import { CartContext } from '../cart.js'


function AddItemButton({product}) {
  const { addToCart } = useContext(CartContext)
  return (
    <button key={product.id} onClick={() => addToCart(product)}>
      Add to Cart
    </button>
  );
}

export default function Products() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/products')
    .then((res) => {
      console.log(res);
      return res.json();
    })
    .then((data) => {
      setProducts(data);
    });
  }, []);

  const listItems = products.map(product =>
    <Product key={product.id} product={product}/>
  );

  return (
    <Container >
      <Row md={3}>
        {listItems}
      </Row>
    </Container>
  );
}

function Product({product}) {
  let price = product.price.toLocaleString("en-US", {style:"currency", currency:"USD"});
  return (
    <>
      <Card style={{ width: '18rem' }}>
        <Card.Img variant="top" src="https://m.media-amazon.com/images/I/514YXEQ8RwL._MCnd_AC_.jpg" />
        <Card.Body>
          <Card.Title>{product.name}</Card.Title>
          <Card.Title>
            {price}
          </Card.Title>
          <Card.Text>
            {product.description}
          </Card.Text>
          <AddItemButton product={product} />
        </Card.Body>
      </Card>
    </>
  );
}