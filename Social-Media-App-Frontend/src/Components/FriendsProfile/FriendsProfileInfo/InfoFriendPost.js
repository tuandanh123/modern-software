import React from 'react'
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import FavoriteRoundedIcon from '@mui/icons-material/FavoriteRounded';
import ShareOutlinedIcon from '@mui/icons-material/ShareOutlined';
import SendRoundedIcon from '@mui/icons-material/SendRounded';
import SentimentSatisfiedRoundedIcon from '@mui/icons-material/SentimentSatisfiedRounded';
import MessageRoundedIcon from '@mui/icons-material/MessageRounded';
import { useState } from 'react';
import moment from "moment"

import {LiaFacebookF} from "react-icons/lia"
import {FiInstagram} from "react-icons/fi"
import {BiLogoLinkedin} from "react-icons/bi"
import {AiFillYoutube} from "react-icons/ai"
import {RxTwitterLogo} from "react-icons/rx"
import {FiGithub} from "react-icons/fi"

import img1 from "../../../assets/Following/tuandanh.jpg"
import img2 from  "../../../assets/Following/thuyan.png"
import img3 from  "../../../assets/Following/P.png"
import Comments from '../../Comments/Comments';
import Profile from "../../../assets/profile.jpg"


const InfoFriendPost = ({val}) => {

    const [comments,setComments] =useState([
        {
            id:1,
            profilePic:img1,
            likes:23,
            username:"TuanDanh",
            time:"3 Hours Ago",
            comment:"I belive you, bro <3."
        },
        {
            id:2,
            profilePic:img2,
            likes:5,
            username:"Thuy An",
            time:"1 Hour Ago",
            comment:"Cô tin tưởng rất nhiều ở em cố lên !!."
        },
        {
            id:3,
            profilePic:img3,
            likes:50,
            username:"Le Tan Phat",
            time:"30 Mins Ago",
            comment:"stupid man!!, cậu nghĩ cậu làm được h? :D"
        }
    ])
    

  const [like,setLike] =useState(val.like)
  const [unlike,setUnlike] =useState(false)

  const [filledLike,setFilledLike] =useState(<FavoriteBorderOutlinedIcon />)
  const [unFilledLike,setUnFilledLike] =useState(false)

  const handlelikes=()=>{
    setLike(unlike ? like -1 :like +1)
    setUnlike(!unlike)

    setFilledLike(unFilledLike ?   <FavoriteBorderOutlinedIcon /> : <FavoriteRoundedIcon />)
    setUnFilledLike(!unFilledLike)
  }

  const [showComment,setShowComment] = useState(false)

  const [commentInput,setCommentInput] =useState("")

  const handleCommentInput=(e)=>{
     e.preventDefault()

    const id=comments.length ? comments[comments.length -1].id +1 : 1
    const profilePic =Profile
    const username="Tuan Danh"
    const comment =commentInput
    const time= moment.utc(new Date(), 'yyyy/MM/dd kk:mm:ss').local().startOf('seconds').fromNow()

    const commentObj ={
      id:id,
      profilePic:profilePic,
      likes:0,
      username:username,
      comment:comment,
      time:time
    }
    const insert =[...comments,commentObj]
    setComments(insert)
    setCommentInput("")
  }

  const [socialIcons,setSocialIcons] = useState(false)

  return (
    <div className='post' style={{marginTop:"10px",marginBottom:"65px"}}>
    <div className='post-header'>

      <div className='post-user'>
          <img src={val.profilepicture} className='p-img' alt="" />
          <h2>{val.username}</h2>
          <p  className='datePara'>{val.datetime}</p>
      </div>
       
       
     </div>

      <p className='body'>{val.body}</p>

      <img src={val.img} alt="" className='post-img' />

    <div className="post-foot">
     <div className="post-footer">
      <div className="like-icons">
        <p className='heart' 
          onClick={handlelikes}
          style={{marginTop:"5px"}}
        >
            {filledLike}
        </p>

        <MessageRoundedIcon 
          onClick= {()=>setShowComment(!showComment)}
          className='msg'  
        />

        <ShareOutlinedIcon 
          onClick={()=>setSocialIcons(!socialIcons)}
          className='share'  
        />
        {socialIcons && (
          
          <div className="social-buttons">        
    
            <a href="http://www.facebook.com" target="blank" className="social-margin"> 
              <div className="social-icon facebook">
                <LiaFacebookF className='social-links'/>
              </div>
            </a>
            
            <a href="https://pinterest.com/" target="blank"  className="social-margin">
              <div className="social-icon instagram">
                <FiInstagram className='social-links'/>
              </div>
            </a>
            
            <a href="http://linkedin.com/" className="social-margin" target="blank">
              <div className="social-icon linkedin">
                <BiLogoLinkedin className='social-links'/>
              </div> 
            </a>
         
            <a href="https://github.com/"  target="blank"  className="social-margin">
              <div className="social-icon github">
                <FiGithub className='social-links'/>
              </div>
            </a>
            
            <a href="http://youtube.com/" target="blank"  className="social-margin">
              <div className="social-icon youtube">
              <AiFillYoutube className='social-links'/>
              </div> 
            </a>
      
            <a href="http://twitter.com/" target="blank" className="social-margin">
              <div className="social-icon twitter">
              <RxTwitterLogo className='social-links'/>
              </div> 
            </a>
       </div>
      )}
      </div>
      

      <div className="like-comment-details">
        <span className='post-like'>{like} people like it,</span>
        <span className='post-comment'>{comments.length} comments</span>
      </div>
      
     {showComment && (<div className="commentSection">
      <form onSubmit={handleCommentInput}>
        <div className="cmtGroup">
            <SentimentSatisfiedRoundedIcon className='emoji'
            />
            
            <input 
            type="text" 
            id="commentInput"
            required
            placeholder='Add a comment...'
            onChange={(e)=>setCommentInput(e.target.value)}
            value={commentInput}
             />
            
            <button type='submit'><SendRoundedIcon className='send' /></button> 
        
        </div>
      </form>

      <div className="sticky">
        {comments.map((cmt)=>(
          <Comments 
          className="classComment"
          cmt={cmt}
          key={cmt.id}
          />
        ))}
        </div>
      </div>
      )}

    </div>     
  </div>
</div>
  )
}

export default InfoFriendPost