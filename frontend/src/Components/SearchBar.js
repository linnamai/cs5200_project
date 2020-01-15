import React, { Component } from 'react';
import Header from './Header';
import { Link } from 'react-router-dom';

class SearchBar extends Component {
    constructor() {
        super();
        this.state = {
            InputValue: "",
            dataSrc: null
        }
    }

    handleGetInputValue = (e) => {
        this.setState({
            InputValue: e.target.value,
        })
    };

    searchClick () {
        let key = 'dishName';
        let value = this.state.InputValue;
        const init = {
            method: 'GET',
        };

        fetch(
            `http://localhost:8080/RestaurantList?${key}=${value}`, init
        ).then(res => res.json())
            .then (data => {
                this.setState({
                    dataSrc: data
                })

            }).catch(e => console.log('error',e))
    }

    renderList(data) {
        const restaurantsElements = [];
        for (let item of data) {
            var path={
                pathname:'/restaurant',
                state:item,
            };
            // console.log("------------"+item.restaurantId);
            // this.props.history.push(path);
            restaurantsElements.push(
                <div className={"panel panel-default"} key={item.restaurantId}>
                    <Link to={path} className="panel-title">{item.name}</Link>
                    <div className="panel-body">{item.streetAddress}</div>
                </div>
            )
        }
        return (
            <div>
                <Header/>
                <div className="wrapper">
                    <div className="input-group">
                        <input type="text" value={this.state.InputValue} onChange={this.handleGetInputValue}
                               className="form-control"  placeholder="Search the dish...🥘🍣🥗🍱🍤🥪🌮🍦🍡🍜"/>
                        <span className="input-group-btn">
                         <button className="btn btn-default" onClick={this.searchClick.bind(this)}>Search</button>
                      </span>
                    </div>
                    <div>{restaurantsElements}</div>
                </div>
            </div>

        );
    }

    renderLoadingView(){
        console.log("renderLoadingView()");
        return (
            <div>
                <Header/>
                <div className="wrapper">
                    <div className="input-group">
                        <input type="text" value={this.state.InputValue} onChange={this.handleGetInputValue}
                               className="form-control"  placeholder="Search the dish...🥘🍣🥗🍱🍤🥪🌮🍦🍡🍜"/>
                        <span className="input-group-btn">
                         <button className="btn btn-default" onClick={this.searchClick.bind(this)}>Search</button>
                      </span>
                    </div>
                </div>
            </div>
        );
    }


    render() {
        if (!this.state.dataSrc) {
            return this.renderLoadingView();
        }
        return this.renderList(this.state.dataSrc)
    }
}


export default SearchBar;
