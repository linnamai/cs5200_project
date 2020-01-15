import React, { Component } from 'react';
import Header from "./Header";
import {Popconfirm, message,DatePicker,TimePicker,Input} from 'antd';

class CreateReservation extends Component {
    constructor(props) {
        super(props);
        var restaurant = this.props.location.state.passrestaurant;

        this.state = {
            partySize: '',
            startTime: '',
            endTime: '',
            restaurant: restaurant,
            name: restaurant.name,
        }
    }

    handleChange = event => {
        const { value, name } = event.target

        this.setState({
            [name]: value
        })
    }

    handleSubmit = e => {
        console.log(`${this.state.partySize} ${this.state.startTime} ${this.state.endTime}`);

        let init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                customer: {
                    state: null,
                    zip: 0,
                    averageRating: 3.71,
                    yelpId: "bc8C_eETBWL0olvFSJJd0w",
                    reviewCounts: 16,
                    city: null,
                    streetAddress: null,
                    userName: "David",
                    password: "1003",
                    lastName: null,
                    firstName: "David",
                    userId: 3
                },
                restaurant: this.state.restaurant,
                partySize: this.state.partySize,
                startTime: this.state.startTime,
                endTime: this.state.endTime,
                status: 'Pending',
            })
        }

        fetch(
            `http://localhost:8080/createReservation`, init
        ).then(res => res.json())
            .then (data => {
                message.success("Reservation is successfully created!🎉🎉🎉");

            }).catch(e => console.log('error',e))
    }

    render() {
        return (
            <div>
                <Header/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <h4>Create Reservation</h4>
                        <div className="create-form">
                            <li>Restaurant Name: {this.state.name}</li>
                            <li>
                                <span>Party Size:</span>
                                <input type="text"
                                       name="partySize"
                                       value={this.state.partySize}
                                       onChange={this.handleChange}
                                ></input>
                            </li>
                            <li>
                                <span>Start Time:</span>
                                <input type="text"
                                       name="startTime"
                                       value={this.state.startTime}
                                       onChange={this.handleChange}
                                ></input>
                            </li>
                            <li>
                                <span>End Time:</span>
                                <input type="text"
                                       name="endTime"
                                       value={this.state.endTime}
                                       onChange={this.handleChange}
                                ></input>
                            </li>
                        </div>
                        <br/>
                        <button className="btn btn-success"
                                onClick={this.handleSubmit}
                        >Submit</button>
                    </div>
                </div>
            </div>

        );
    }

}

export default CreateReservation;
