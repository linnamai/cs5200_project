import Header from "./Header";
import React, {Component} from "react";
import MerchantHeader from "./MerchantHeader";

class MyOrder extends Component {
    constructor() {
        super();
        this.state = {
            orders:''
        };
    }

    render() {
        //get orders for restaurant
        const ordersElements = [];
        const init = {
            method: 'GET',
        };

        let key = 'customerId';
        let value = 3;
        fetch(
            `http://localhost:8080/getOrderByCustomerId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                this.setState({
                                  orders: data
                              })

            }).catch(e => console.log('error', e));

        if (this.state.orders !== "") {
            for (let order of this.state.orders) {
                console.log(order);
                ordersElements.push(
                    <tr>
                        <td>{order.restaurant.name}</td>
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
                <Header/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div className="panel panel-default" id="menu-panel">
                            <p>Orders:</p>

                            <table className="table" id="order-table">
                                <th>Restaurant Name</th>
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

export default MyOrder;