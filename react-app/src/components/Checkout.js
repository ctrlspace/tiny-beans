import {useNavigate} from "react-router-dom";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import React, {useContext, useRef, useState } from "react";
import { CartContext } from '../cart.js';

export default function Checkout()  {
  const { getDisplayTotal } = useContext(CartContext);
  const {cartItems, clearCart} = useContext(CartContext);
  const navigate = useNavigate();
  const purchaseForm = useRef(null)
  const [orderResponse, setOrderResponse] = useState({});
  const [processing, setProcessing] = useState(false);
  const [validForm, setValidForm] = useState(false);

  function doServerCall() {
    const form = purchaseForm.current;
    console.log(cartItems);
    let purchaseCart = {
      items: cartItems,
      customer: {
        name: form.name.value,
        email: form.email.value,
        address: {
          address: form.address.value,
          city: form.city.value,
          state: form.state.value,
          zip: form.zip.value
        }
      },
      cc: {
        number: form.cardNumber.value,
        expiry: form.expiry.value,
        cvc: form.cvc.value,
      }
    }
    console.log(purchaseCart)
    console.log(JSON.stringify(purchaseCart))
    fetch(
      'http://localhost:8080/api/v1/cart/purchase', {
        method: 'post',
        headers: new Headers({'content-type': 'application/json'}),
        body: JSON.stringify(purchaseCart)
      }
    )
    .then((res) => {
      return res.json();
    })
    .then((data) => {
      console.log("post data", data)
      if(data.success) {
        clearCart();
        navigate('/confirmation', {state: data});
      } else {
        setOrderResponse(data);
      }

    })
    .catch(error =>
      console.log("Oops! Is your server disconnected?!"))
    .finally(() => {
        setProcessing(false);
        console.log('done');
      }
    );

  }

  function handleSubmit(event) {
    event.preventDefault();
    setProcessing(true)
    doServerCall();
  }

  function validateForm(event) {
    setOrderResponse({});
    const form = purchaseForm.current;
    if(form.name.value &&
    form.email.value &&
    form.address.value &&
    form.city.value &&
    form.state.value &&
    form.zip.value &&
    form.cardNumber.value &&
    form.expiry.value &&
    form.cvc.value) {
      setValidForm(true);
    } else {
      setValidForm(false);
    }
  }

  function Required(){
    if(!validForm) {
      return (
        <>
          <div className="error-form">
            <p>All Fields are Required.</p>
          </div>
        </>
      );
    } else {
      return (
        <>
        </>
      );
    }
  }

  function Error(){
    if(orderResponse && orderResponse.errorMessage && !orderResponse.success) {
      if(orderResponse.stripeError) {
        return (
          <>
            <div className="error-processing">
              <p>There was an error processing your order.</p>
              <p>{orderResponse.errorMessage}</p>
            </div>
          </>
        );
      } else {
        return (
          <>
            <div className="error-processing">
              <p>There was an error processing your order.</p>
            </div>
          </>
        );
      }
    } else {
      return <p></p>
    }
  }

  function PurchaseButton() {
    if(!processing && validForm) {
      return(
        <button className="valid-button" type="submit">Pay {getDisplayTotal()}</button>
      );
    } else if(!processing && !validForm) {
      return (
        <button className="valid-button" disabled={true}>Pay {getDisplayTotal()}</button>
      );
    } else {
      <button className="valid-button" disabled={true}>Processing...</button>
    }
  }

  return (
    <>
      <form ref={purchaseForm} onSubmit={handleSubmit} className="component" >
        <h2>Make Payment</h2>
        <Container className="credit-card">
          <Row>
            <Col>
              <div className="line">
                <input
                  id="name"
                  placeholder="Name"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
            <Col>
              <div className="line">
                <input
                  id="email"
                  placeholder="Email"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
          </Row>
          <Row>
            <div className="line">
              <input
                id="address"
                placeholder="Address"
                onChange={event => {
                  validateForm(event.target.value);
                }}
              />
            </div>
          </Row>
          <Row>
            <Col xs={6}>
              <div className="line">
                <input
                  id="city"
                  placeholder="City"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
            <Col xs={3}>
              <div className="line">
                <input
                  id="state"
                  placeholder="State"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
            <Col xs={3}>
              <div className="line">
                <input
                  id="zip"
                  placeholder="ZIP"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
          </Row>
          <Row>
            <div className="line">
              <input
                id="cardNumber"
                placeholder="Card Number"
                onChange={event => {
                  validateForm(event.target.value);
                }}
              />
            </div>
          </Row>
          <Row>
            <Col>
              <div className="line">
                <input
                  id="expiry"
                  placeholder="Expiration"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
            <Col>
              <div className="line">
                <input
                  id="cvc"
                  placeholder="CVC"
                  onChange={event => {
                    validateForm(event.target.value);
                  }}
                />
              </div>
            </Col>
          </Row>
        </Container>
        <PurchaseButton />
      </form>
      <Required />
      <Error />
    </>
  );
}