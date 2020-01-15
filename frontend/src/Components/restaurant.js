import React, {Component} from 'react';
import Header from "./Header";
import {Link} from 'react-router-dom';

class Restaurant extends Component {
    constructor(props) {
        super(props);
        this.state = {
            dishes: '',
            deliverableText: '',
        }

    }

    render() {
        var restaurant = this.props.location.state;
        const dishesElements = [];
        if (restaurant.deliverable == false) {
            this.state.deliverableText = 'No';
        } else {
            this.state.deliverableText = 'Yes';
        }

        var path = {
            pathname: '/createReservation',
            state: {
                passrestaurant: restaurant,
            },
        }

        // get menu
        let key = 'restaurantId';
        let value = restaurant.restaurantId;
        const init = {
            method: 'GET',
        };

        fetch(
            `http://localhost:8080/getDishesByRestaurantId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                this.setState({
                                  dishes: data,
                              })

            }).catch(e => console.log('error', e))

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
                <Header/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div className="panel panel-default" id="restaurant-info">
                            <p>Restaurant Information:</p>
                            <div>Name: {restaurant.name}</div>
                            <div>category: {restaurant.category}</div>
                            <div>Address: {restaurant.streetAddress}, {restaurant.city}, {restaurant.state}</div>
                            <div>Deliverable: {this.state.deliverableText}</div>
                            <div>DeliveryPrice: ${restaurant.deliveryPrice}</div>
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
                        <div  id="restaurant-operation">
                            <button className="btn pleyt-btn">Review</button><br/>
                            <Link to={path}>
                                <button className="btn pleyt-btn">Make Reservation</button>
                            </Link><br/>
                            <button className="btn pleyt-btn">Order Food</button>
                            <br/>
                        </div>

                    </div>
                </div>
            </div>
        );
    }
}

export default Restaurant;
