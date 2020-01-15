import React from 'react';
import {BrowserRouter as Router,Route} from 'react-router-dom';
import { createBrowserHistory as History} from 'history';
import SearchBar from './Components/SearchBar';
import Restaurant from './Components/restaurant';
import Reservation from "./Components/Reservation";
import CreateReservation from "./Components/CreateReservation";
import MyRestaurant from "./Components/MyRestaurant";
import ReviewForMerchants from "./Components/ReviewForMerchants";
import OrderForMerchants from "./Components/OrderForMerchants";
import MyOrder from "./Components/MyOrder";
import MyCreditCard from "./Components/MyCreditCard";

class App extends React.Component {
    render(){
        return(
            <Router history={History}>
                <div>
                    <Route exact path="/" component={SearchBar} />
                    <Route path="/restaurant" component={Restaurant} />
                    <Route path="/myReservation" component={Reservation} />
                    <Route path="/createReservation" component={CreateReservation} />
                    <Route path="/myAccount" component={MyCreditCard} />
                    {/*-----for merchant------*/}
                    <Route path="/merchant" component={MyRestaurant} />
                    <Route path="/reviewsForMyRestaurant" component={ReviewForMerchants} />
                    <Route path="/ordersForMyRestaurant" component={OrderForMerchants} />
                    <Route path="/getOrderByCustomerId" component={MyOrder} />
                </div>
            </Router>
        )
    }
}

export default App;