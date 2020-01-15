import React, { Component } from 'react';
import Header from "./Header";
import {Popconfirm, message,DatePicker,TimePicker,Input} from 'antd';

class Reservation extends Component {
    constructor() {
        super();
        this.state = {
            dataSrc:"",
            showEdit:false,
            editForm: {
                restaurant:{
                    name: "",
                },
                partySize: "",
                startTime: "",
                endTine: "",
            },
            startTimeStr: "",
            startDateStr: "",
            endDateStr: "",
            endTimeStr: "",
            newPartySize: "",
            refresh:false,
        }

        this.getData()
    }

    getData () {
        let key = 'customerId';
        let value = 3;
        const init = {
            method: 'GET',
        };
        fetch(
            `http://localhost:8080/getReservationsByCustomerId?${key}=${value}`, init
        ).then(res => res.json())
            .then(data => {
                this.setState({
                                  dataSrc: data
                              })

            }).catch(e => console.log('error', e))
    }

    handleDelete(index) {
        console.log("click which one? " + index);

        const init_delete = {
            method: 'GET',
        }

        fetch(
            'http://localhost:8080/deleteReservation?reservationId='+index, init_delete
        ).then(res => res.json()
            .then(data => {
                this.setState({
                    dataSrc:this.state.dataSrc.filter(item => item.reservationId !== index)
                })
            })
        );

        message.success('the record has been deleted');
    }

    handleCancel() {
       message.error('clicked cancel');
       console.log("click cancel");
    }

    handleEditBtn(item) {
        if (item.status == "Finished") {
            this.setState({
                showEdit: false,
            })
            message.error("can't edit a Finished reservation");

        } else {
            this.setState({
                showEdit: true,
                editForm: item,
            })
        }
    }

    handleEditSubmit() {

        var newStartTime = this.state.startDateStr + " " + this.state.startTimeStr + ":00";
        var newEndTime = this.state.endDateStr + " " + this.state.endTimeStr + ":00"
        var newPartySize = this.state.newPartySize;
        this.setState({
            showEdit: false,
        })

        console.log("reservationId="+this.state.editForm.reservationId);
        let init = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                reservationId: this.state.editForm.reservationId,
                restaurant: this.state.editForm.restaurant,
                partySize: newPartySize,
                startTime: newStartTime,
                endTime: newEndTime,
            })
        }

        fetch(
            `http://localhost:8080/updateReservation`, init
        ).then(res => res.json())
            .then(data => {
                message.success("the reservation has been updated! 🎉🎉🎉");
                this.setState({
                    refresh:true,
                })

            }).catch(e => console.log('error', e))
    }

    handleSizeChange = (e) => {
        this.setState({
            newPartySize: e.target.value,
        })
    }

    handleStartChange(time, timeString) {
        this.setState({
            startDateStr: timeString,
        })
    }

    handleStartTimeChange(time, timeString) {
        this.setState({
            startTimeStr: timeString,
        })
    }

    handleEndChange(time, timeString) {
        this.setState({
            endDateStr: timeString,
        })
    }

    handleEndTimeChange(time, timeString) {
        this.setState({
            endTimeStr: timeString,
        })
    }


    render () {

        const reservationsElements = [];
        const editElement = [];
        const format = 'HH:mm';

        if (this.state.dataSrc != "") {
            for (let item of this.state.dataSrc) {
                reservationsElements.push(
                    <div className="panel panel-default reserv-card" key={item.reservationId}>
                        <div>Restaurant Name: {item.restaurant.name}</div>
                        <div>Start Time: {item.startTime}</div>
                        <div>End Time: {item.endTime}</div>
                        <div>Party Size: {item.partySize}</div>
                        <div>Reservation Status: {item.status}</div>
                        <Popconfirm placement="topLeft"
                                    title="Are you sure you want to delete this reservation?"
                                    onConfirm={this.handleDelete.bind(this, item.reservationId)}
                                    onCancel={this.handleCancel.bind(this)}>
                            <a href="#">😢delete</a>
                        </Popconfirm>
                         <button type="button" className="btn btn-link"
                                 onClick={this.handleEditBtn.bind(this,item)}>✏️edit️</button>
                    </div>
                )
            }
        } else {
            reservationsElements.push(
                <div key="0">There is no reservation yet.</div>
            )
        }

        if (this.state.showEdit) {
            editElement.push(
                <div className="panel panel-default create-form">
                    <div>Restaurant Name: {this.state.editForm.restaurant.name}</div>
                    <li>
                        <span style={{marginRight:"10px",}}>Party Size:</span>
                        <Input type="text"
                               name="partySize"
                               defaultValue={this.state.editForm.partySize}
                               onChange={this.handleSizeChange}
                        ></Input>
                    </li>
                    <li>
                        <span style={{marginRight:"8px",}}>Start Time:</span>
                        <DatePicker onChange={this.handleStartChange.bind(this)}/>
                        <TimePicker format={format} minuteStep={15}
                                    onChange={this.handleStartTimeChange.bind(this)}
                                    style={{width:"150px",marginLeft:"10px",}}/>
                    </li>
                    <li>
                        <span style={{"marginRight":"15px",}}>End Time:</span>
                        <DatePicker onChange={this.handleEndChange.bind(this)}/>
                        <TimePicker format={format} minuteStep={15}
                                    onChange={this.handleEndTimeChange.bind(this)}
                                    style={{width:"150px",marginLeft:"10px",}}/>
                    </li>
                    <button className="btn btn-link edit-submit-btn"
                            onClick={this.handleEditSubmit.bind(this)}>Submit</button>
                </div>
            )
        }

        return (
            <div>
                <Header/>
                <div className="wrapper">
                    <div className="container-fluid">
                        <div id="display-panel">
                            <p>Manage your reservations:</p>
                            <div>{reservationsElements}</div>
                        </div>
                        <div id="edit-panel">
                            {editElement}
                        </div>
                    </div>
                </div>
            </div>
        )

    }
}

export default Reservation;
