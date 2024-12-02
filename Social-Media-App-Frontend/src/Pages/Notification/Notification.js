import React from 'react'
import "../Notification/Notification.css"
import img1 from "../../assets/DP/sontung.jpg"
import img2 from "../../assets/DP/jack.jpg"
import img3 from "../../assets/DP/Dinh.png"
import img4 from "../../assets/DP/hoa.jpg"
import img5 from "../../assets/DP/Linh.jpg"
import {AiOutlineHome} from "react-icons/ai"
import ProfileImg from "../../assets/profile.jpg"
import { Link } from 'react-router-dom'

const Notification = () => {
  
  return (
    <div className="noti-overall">
      <div className='nav-section'>
        <Link to="/home" style={{textDecoration:"none"}} className='noti-div'><AiOutlineHome className='noti-Home-Icon'/></Link>
        <Link to="/profile" style={{textDecoration:"none"}}><img src={ProfileImg} alt="" /></Link>
      </div>

    <div className="notification-group">
      <h1>notification</h1>
      <div className="notification-section">
        <div className="notification-msg">
            <img src={img1} alt="" />
            <p>Son Tung liked <span className='noti-like'>your profile picture</span><small><br />10 mins ago</small></p>
        </div>

        <div className="notification-msg">
            <img src={img2} alt="" />
            <p>Jack commented <span className='noti-like'>your profile picture</span><br /><small>1 day ago</small></p>
        </div>

        <div className="notification-msg">
            <img src={img3} alt="" />
            <p>Tuan Danh's wife liked <span className='noti-like'>your cover picture</span><br /><small>20s ago</small></p>
        </div>

        <div className="notification-msg">
            <img src={img4} alt="" />
            <p>Hoang Lien Hoa commented <span className='noti-like'>your profile picture</span><br /><small>5h ago</small></p>
        </div>

        <div className="notification-msg">
            <img src={img5} alt="" />
            <p>Tran Ha Linh reacted the emtion in  <span className='noti-like'>your profile picture</span><br /><small>1 min ago</small></p>
        </div>
      </div>
    </div>
    </div>
  )
}

export default Notification