import fistPump from "../fistPump.jpg";
import React from "react";
import { useLocation } from "react-router-dom";

function PurchaseResponse({ response }) {
  if (response) {
    if(response.success) {
      return (
        <>
        <p>Your card was charged: {response.cost.toLocaleString("en-US", {style: "currency", currency: "USD"})}</p>
        <p>Your order ID is {response.orderId}</p>
        <p>Your payment ref is {response.paymentId}</p>
        </>
      );
    } else {
      return (
        <>
          <p>There was an issue processing your order</p>
        </>
      );
    }
  }
  return <p>An Unknown Error Occured</p>;
}


export default function Confirmation() {

  const location = useLocation()
  const response = location.state
  return (
    <div>
      <h2>Success!!</h2>
      <img src={fistPump} alt="fistPump"/>
      <PurchaseResponse response={response}/>
    </div>
  );
}
