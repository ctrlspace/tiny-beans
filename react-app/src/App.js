import './App.css';
import React, {useContext} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { BrowserRouter, Routes, Route, useNavigate } from 'react-router-dom';
import Products from './components/Products.js'
import Checkout from './components/Checkout.js'
import Confirmation from './components/Confirmation.js'
import { CartContext } from './cart.js'

function App() {
  const { getDisplayTotal, clearCart, cartHasItems } = useContext(CartContext);

  function AppNavBar() {
    const navigate = useNavigate();

    function doClearCart() {
      clearCart();
      navigate('/');
    }

    if(cartHasItems()) {
      return (
        <Navbar className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="/">Select a Product</Navbar.Brand>
            <Navbar.Brand href="/checkout">Checkout | Total: {getDisplayTotal()}</Navbar.Brand>
            <Navbar.Brand onClick={() => doClearCart()}>Empty Cart</Navbar.Brand>
          </Container>
        </Navbar>
      );
    } else {
      return (
        <Navbar className="bg-body-tertiary">
          <Container>
            <Navbar.Brand href="/">Select a Product</Navbar.Brand>
            <Navbar.Brand>Cart is Empty</Navbar.Brand>
          </Container>
        </Navbar>
      );
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Tiny Beans Store Front
        </p>

      </header>
      <div>
        <BrowserRouter >
          <AppNavBar />
          <Routes>
            <Route path="/" element={<Products />}/>
            <Route path="/checkout" element={<Checkout />}/>
            <Route path="/confirmation" element={<Confirmation />}/>
          </Routes>
        </BrowserRouter>
      </div>
    </div>
  );
}

export default App;
