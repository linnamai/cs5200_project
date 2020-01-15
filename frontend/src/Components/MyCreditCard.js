import React, {Component} from "react";
import Header from "./Header";

class MyCreditCard extends Component{
    constructor() {
        super();
        this.state = {
            creditCards:""
        };
        this.getData();
    }

    getData() {
        //get credit cards for customer
        const init = {
            method: 'GET',
        };

        let key = 'customerId';
        let value = 3;
        fetch(
            `http://localhost:8080/getCreditCardByCustomerId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                console.log(data);
                this.setState({
                                  creditCards: data
                              })
            }).catch(e => console.log('error', e));
    }

    render() {

        const creditCardsElements = [];
        const userInfo = [];
        if (this.state.creditCards !== "") {
                creditCardsElements.push(
                    <tr>
                        <td>{this.state.creditCards.customer.userName}</td>
                        <td>{this.state.creditCards.cardNumber}</td>
                        <td>{this.state.creditCards.expirationYear}</td>
                        <td>{this.state.creditCards.expirationMonth}</td>
                    </tr>
                );
                userInfo.push(
                    <tr>
                        <td>{this.state.creditCards.customer.userName}</td>
                        <td>{this.state.creditCards.customer.firstName}</td>
                        <td>{this.state.creditCards.customer.averageRating}</td>
                    </tr>
                )
        }

        return (
            <div>
                <Header/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div className="panel panel-default" id="info-panel">
                            <p>Customer Info:</p>
                            <table className="table" id="customerInfo-table">
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Average Rating</th>
                            {userInfo}
                            </table>
                        </div>


                        <div className="panel panel-default" id="menu-panel">
                            <p>Credit Card:</p>
                            <table className="table" id="card-table">
                                <th>First Name</th>
                                <th>Card Number</th>
                                <th>Expiration Year</th>
                                <th>Expiration Month</th>
                                {creditCardsElements}
                            </table>

                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default MyCreditCard;