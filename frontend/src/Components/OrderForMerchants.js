import React, {Component} from "react";
import MerchantHeader from "./MerchantHeader";

class OrderForMerchants extends Component {
    constructor() {
        super();
        this.state = {
            restaurant:"",
            orders:""
        };
        this.getData()
    }

    getData () {
        //get restaurant
        let key = 'merchantId';
        let value = 3;
        const init = {
            method: 'GET',
        };
        fetch(
            `http://localhost:8080/getRestaurantsByMerchantId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                this.setState({
                                  restaurant: data
                              })
            }).catch(e => console.log('error', e));
    }

    render() {
        //get orders for restaurant

        const ordersElements = [];
        const init = {
            method: 'GET',
        };

        if (this.state.restaurant !== "") {
            let key = 'restaurantId';
            let value = this.state.restaurant.restaurantId;
            fetch(
                `http://localhost:8080/getOrdersByRestaurantId?${key}=${value}`, init
            ).then(res => res.json())
                .then(data => {
                    this.setState({
                                      orders: data
                                  })

                }).catch(e => console.log('error', e));
        }

        if (this.state.orders !== "") {
            for (let order of this.state.orders) {
                ordersElements.push(
                    <tr>
                        <td>{this.state.restaurant.name}</td>
                        <td>{order.customer.userName}</td>
                        <td>${order.orderPrice}</td>
                        <td>{order.status}</td>
                        <td>{order.createTime}</td>
                        <td>{order.endTime}</td>
                    </tr>
                )
            }
        }

        return (
            <div>
                <MerchantHeader/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div className="panel panel-default" id="menu-panel">
                            <p>Orders:</p>

                            <table className="table" id="order-table">
                                <th>Restaurant Name</th>
                                <th>Customer Username</th>
                                <th>Order Price</th>
                                <th>Order Status</th>
                                <th>Create Time</th>
                                <th>End Time</th>
                                {ordersElements}
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default OrderForMerchants;