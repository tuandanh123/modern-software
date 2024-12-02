import React, { useState } from 'react';
import { Modal } from '@mantine/core';
import Fimg1 from "../../../../assets/DP/dieuthuyen.jpg"
import Fimg2 from "../../../../assets/DP/hanh.jpg"
import Fimg3 from "../../../../assets/DP/Van.jpg"
import Fimg4 from "../../../../assets/DP/truc.jpg"
import Fimg5 from "../../../../assets/DP/Dao.jpg"

const FollowingMore = ({showMore,setShowMore}) => {
    
    const FollowingRemainingData=[
        {   
            id:1,
            name:"Dieu Thuyen",
            username:"@lili",
            img:Fimg1
        },
        {  
            id:2,
            name:"Hanh",
            username:"@Pinkee",
            img:Fimg2
        },
        {   id:3,
            name:"Van",
            username:"@BrandonBran",
            img:Fimg3
        },
        {   
            id:4,
            name:"Tu Truc",
            username:"@Chutki",
            img:Fimg4
        },
        {  
            id:5,
            name:"Thanh Dao",
            username:"@JassicaMia",
            img:Fimg5
        }
    ]
    
    


  return (
    
    <>
      <Modal
      className='modelShowMore'
      radius="8px"
      opened={showMore}
      onClose={()=>setShowMore(false)}
      transitionProps={{ transition: 'fade', duration: 200 }}
      title="Who Is Following You"
      centered
      padding="20px"
      zIndex={2000}
      
    >
        {FollowingRemainingData.map((val)=>(
                  <div key={val.id} style={{marginTop:"20px"}} className="following-people">
                  <div className="following-details">
                    <img src={val.img} alt="" />
                      <div className="following-name-username">
                        <h3>{val.name}</h3>
                        <p>{val.username}</p>
                      </div>
                  </div>
        
                  <button className='Rbtn' style={{background:"linear-gradient(107deg, rgb(255, 67, 5) 11.1%, rgb(245, 135, 0) 95.3%)"}}>Follow</button>
            </div>
        
        ))}

    
    </Modal>
    </>

  
  )
}

export default FollowingMore