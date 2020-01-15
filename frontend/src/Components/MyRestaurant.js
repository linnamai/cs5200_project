import React, { Component } from 'react';
import MerchantHeader from "./MerchantHeader";

class MyRestaurant extends Component {
    constructor() {
        super();
        this.state = {
            restaurant:'',
            dishes: '',
            deliverableText: '',
        };

        this.getData()
    }

    getData () {
        let key = 'merchantId';
        let value = 3;
        const init = {
            method: 'GET',
        };
        fetch(
            `http://localhost:8080/getRestaurantsByMerchantId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {

                if(data.deliverable == true) {
                    this.setState({
                        restaurant: data,
                        deliverableText: 'No',
                    })
                } else {
                    this.setState({
                        restaurant: data,
                        deliverableText: 'Yes',
                    })
                }


            }).catch(e => console.log('error', e))

    }

    render() {
        const dishesElements = [];

        // get menu
        const initDish = {
            method: 'GET',
        };

        if (this.state.restaurant !== "") {
            let key1 = 'restaurantId';
            let value1 = this.state.restaurant.restaurantId;
            fetch(
                `http://localhost:8080/getDishesByRestaurantId?${key1}=${value1}`, initDish
            ).then(res => res.json())
                .then(data => {
                    this.setState({
                                      dishes: data,
                                  })

                }).catch(e => console.log('error', e))

        }

        if (this.state.dishes !== "") {

            for (let dish of this.state.dishes) {
                dishesElements.push(
                    <tr>
                        <td>{dish.dishName}</td>
                        <td>{dish.basePrice}</td>
                        <td>{dish.dishTYpe}</td>
                        <td>{dish.description}</td>
                    </tr>
                )
            }
        }


        return (
            <div>
                <MerchantHeader/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div className="panel panel-default" id="restaurant-info">
                            <p>Restaurant Information:</p>
                            <div>Name: {this.state.restaurant.name}</div>
                            <div>category: {this.state.restaurant.category}</div>
                            <div>Address: {this.state.restaurant.streetAddress},
                                {this.state.restaurant.city},
                                {this.state.restaurant.state}</div>
                            <div>Deliverable: {this.state.deliverableText}</div>
                            <div>DeliveryPrice: ${this.state.restaurant.deliveryPrice}</div>
                            <div className="panel panel-default" id="menu-panel">
                                <p>Menu:</p>

                                <table className="table" id="menu-table">
                                    <th>Dish Name</th>
                                    <th>BasePrice</th>
                                    <th>Dish Type</th>
                                    <th>Description</th>
                                    {dishesElements}
                                </table>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default MyRestaurant;
