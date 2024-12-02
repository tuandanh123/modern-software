import {  useState } from 'react'
import Profile from "../../assets/DP/Dinh.png"
import img1 from "../../assets/Post Images/img1.jpg"
import img2 from "../../assets/Post Images/P.jpg"
import img3 from "../../assets/Post Images/Mthu.png"
import img4 from "../../assets/Post Images/binhan.jpg"
import img5 from "../../assets/Post Images/sontung.png"
import img6 from "../../assets/DP/Linh.jpg"
import img7 from "../../assets/DP/jack.jpg"
import imghoa from "../../assets/Post Images/hoa.jpg"





import DPimg1 from "../../assets/DP/img1.jpg"
import DPimg2 from "../../assets/DP/P.png"
import DPimg3 from "../../assets/DP/W.png"
import DPimg4 from "../../assets/DP/binhan.jpg"
import DPimg5 from "../../assets/DP/sontung.jpg"
import DPimg6 from "../../assets/DP/hoa.jpg"

import cover from "../../assets/Info-Dp/img-3.jpg"

import Cover1 from "../../assets/Friends-Cover/cover-1.jpg"
import Cover2 from "../../assets/Friends-Cover/cover-2.jpg"
import Cover3 from "../../assets/Friends-Cover/Mthu.png"
import Cover5 from "../../assets/Friends-Cover/cover-5.jpg"
import Cover7 from "../../assets/Friends-Cover/cover-7.jpg"
import Cover8 from "../../assets/Friends-Cover/cover-8.jpg"
import Cover9 from "../../assets/Friends-Cover/cover-9.jpg"

import Uimg1 from "../../assets/Post Images/NgocDinh.png"
import Uimg2 from "../../assets/Post Images/Linh.jpg"
import Uimg3 from "../../assets/Post Images/jack.jpg"


import "../Home/Home.css"

import Left from "../../Components/LeftSide/Left"
import Middle from "../../Components/MiddleSide/Middle"
import Right from '../../Components/RightSide/Right'
import Nav from '../../Components/Navigation/Nav'
import moment from 'moment/moment'

const Home = ({setFriendsProfile}) => {
  
    const [posts,setPosts] = useState(
        [
          {
            id:1,
            username:"Ngoc Hoang",
            profilepicture:DPimg1,
            img:img1,
            datetime:moment("20240531", "YYYYMMDD").fromNow(),
            body:"In my Opinon, I want to get the job in the big tech companies, you know?",
            like: 44,
            comment:3,
            unFilledLike:true,
            coverpicture:Cover1,
            userid:"@Iamharry",
            ModelCountryName:"USA",
            ModelJobName:"Java Developer",
            ModelJoinedDate:"Joined in 2024-02-28",
            followers:1478
          },
          {
            id:2,
            username:"Le Tan Phat",
            profilepicture:DPimg2,
            img:img2,
            datetime:moment("20230605", "YYYYMMDD").fromNow(),
            body:"Hello guys, it's really cool right ?",
            like: 84,
            comment:3,
            coverpicture:Cover2,
            userid:"@chris777",
            ModelCountryName:"Australia",
            ModelJobName:"Cyber Security",
            ModelJoinedDate:"Joined in 2014-01-17",
            followers:1730
          },
          {
            id:3,
            username:"Tuan Danh's wife",
            profilepicture:DPimg3,
            img:img3,
            datetime:moment("20230813", "YYYYMMDD").fromNow(),
            body:"it's so good today, how about my husband? what's he doing? right now. Tuan Danh ~~",
            like: 340,
            comment:76,
            coverpicture:Cover3,
            userid:"@MinhThu",
            ModelCountryName:"Viet nam",
            ModelJobName:"Python Developer",
            ModelJoinedDate:"Joined in 2022-03-01",
            followers:426
          },
          {
            id:4,
            username:"Ngoc Dinh",
            profilepicture:Profile,
            img:Uimg1,
            datetime:moment("20230310", "YYYYMMDD").fromNow(),
            body:"cute k nè >< , hihi !!. rep cmt tui ib lunnn",
            like: 22,
            comment:3,
            coverpicture:cover,
            userid:"NgocDinh",
            ModelCountryName:"India",
            ModelJobName:"Web Developer in Google",
            ModelJoinedDate:"Joined in 2023-08-12",
            followers:5000
          },
          {
            id:5,
            username:"Binh An",
            profilepicture:DPimg4,
            img:img4,
            datetime:moment("20200101", "YYYYMMDD").fromNow(),
            body:"Do you want to see more myself, guys? Hihi",
            like: 44,
            comment:3,
            coverpicture:Cover5,
            userid:"@laralara",
            ModelCountryName:"London",
            ModelJobName:"CEO in Google",
            ModelJoinedDate:"Joined in 2023-04-15",
            followers:3005
          },
          {
            id:6,
            username:"Tran Ha Linh",
            profilepicture:img6,
            img:Uimg2,
            datetime:moment("20230618", "YYYYMMDD").fromNow(),
            body:"Đừng nghĩ em xấu mà chê, yêu em thì biết nó phê cỡ nào ",
            like: 84,
            comment:3,
            coverpicture:cover,
            userid:"@tuandanh",
            ModelCountryName:"Viet nam",
            ModelJobName:"Web Developer in Google",
            ModelJoinedDate:"Joined in 2023-08-12",
            followers:5000
          },
          {
            id:7,
            username:"Son Tung Mtp",
            profilepicture:DPimg5,
            img:img5,
            datetime:moment("20230505", "YYYYMMDD").fromNow(),
            body:"Mọi người chuẩn bị chào đón ca khúc mới vào tối nay lúc 22h nha cả nhà, i love sky so muchhhhh <3",
            like: 30,
            comment:3,
            coverpicture:Cover7,
            userid:"@kenny80",
            ModelCountryName:"Viet Nam",
            ModelJobName:"Singer",
            ModelJoinedDate:"Joined in 2020-08-09",
            followers:626
          },
          {
            id:8,
            username:"Jack 97",
            profilepicture:img7,
            img:Uimg3,
            datetime:moment("20230219", "YYYYMMDD").fromNow(),
            body:"Do you want to recieve 5 milion thosands vnd ? <3 ",
            like: 340,
            comment:3,
            coverpicture:Cover8,
            userid:"@tuandanh",
            ModelCountryName:"Sai Gon",
            ModelJobName:"Singer",
            ModelJoinedDate:"Joined in 2023-08-12",
            followers:5000

          },
          {
            id:9,
            username:"Hoang Lien Hoa",
            profilepicture:DPimg6,
            img:imghoa,
            datetime:moment("20230404", "YYYYMMDD").fromNow(),
            body:"Where's he is right now, Tuan Danh?",
            like: 844,
            comment:3,
            coverpicture:Cover9,
            userid:"@reyanaRey",
            ModelCountryName:"Viet Nam",
            ModelJobName:"Back End Developer in Microsoft",
            ModelJoinedDate:"Joined in 2020-02-29",
            followers:3599
           }
        ]
      )

      const [body,setBody] =useState("")
      const [importFile,setImportFile] =useState("")
      

      const handleSubmit =(e)=>{
        e.preventDefault()
        
        
        const id =posts.length ? posts[posts.length -1].id +1 :1
        const username="tuandanh"
        const profilepicture=Profile
        const datetime=moment.utc(new Date(), 'yyyy/MM/dd kk:mm:ss').local().startOf('seconds').fromNow()
        const img =images ? {img:URL.createObjectURL(images)} : null
        
        const obj ={id:id,
                   profilepicture:profilepicture,
                   username:username,
                   datetime:datetime,
                   img:img && (img.img),
                   body:body,
                   like:0,
                   comment:0
                  }

        

        const insert =[...posts,obj]
        setPosts(insert)
        setBody("")
        setImages(null)

      }
   
   const [search,setSearch] =useState("")

    
  const [following,setFollowing] =useState("")
        
  const [showMenu,setShowMenu] =useState(false)
  const [images,setImages] =  useState(null)

  return (
    <div className='interface'>
        <Nav 
        search={search}
        setSearch={setSearch}
        showMenu={showMenu}
        setShowMenu={setShowMenu}
        />

    <div className="home">
   
        <Left />

        <Middle 
        handleSubmit={handleSubmit}
        body ={body}
        setBody ={setBody}
        importFile ={importFile}
        setImportFile ={setImportFile}
        posts={posts}
        setPosts={setPosts}
        search={search}
        setFriendsProfile={setFriendsProfile}
        images={images}
        setImages={setImages}

        />

        <Right
        showMenu={showMenu}
        setShowMenu={setShowMenu}
        following={following}
        setFollowing={setFollowing}
        />
    </div>

    </div>
  )
}

export default Home