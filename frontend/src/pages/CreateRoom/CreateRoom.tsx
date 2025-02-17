import styled from 'styled-components';
import RoomForm from '../../components/atoms/Room/RoomForm';
import axios from 'axios';
import { Header } from '../../components/organisms/Header';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../../util/api';

const CreateRoomPageContainer = styled.div`
  h1 {
    font-size: 25px;
    font-weight: 500;
    text-align: center;
    padding-bottom: 15px;
  }
`;

const Container = styled.div`
  padding-top: 7rem;
  height: 100vh;
  display: flex;
  justify-content: center;
`;

const CreateRoomPage = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState('');
  const [memberId, setMemberId] = useState('');
  const imgUrl = useRef('')

  const [selectedFile, setSelectedFile] = useState('');
  const [formError, setError] = useState(false);

  useEffect(() => {
    // 페이지 진입 시 로컬 스토리지 값 확인
    const userInfoString = localStorage.getItem('access_token');
    const usermemberId = localStorage.getItem('member_id');

    if (userInfoString && usermemberId) {
      setToken(JSON.parse(userInfoString));
      setMemberId(JSON.parse(usermemberId));
      console.log(userInfoString);
      console.log(usermemberId);
    } else {
      console.log('스토리지 값 없음');
      alert(`로그인이 필요한 서비스 입니다!`)
      navigate(`/signin`);
    }
  }, []);

  const sendFormData = async (data: any) => {
    const formData = new FormData();
    formData.append('image', selectedFile);
  
    api
      .post(`${import.meta.env.VITE_BASE_URL}thumbnail`, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      })
      .then((response) => {
        // 요청 성공 시 처리
        imgUrl.current = response.data;
        console.log('@@@이미지 성공@@@');
        setError(false);
        console.log(imgUrl.current);
  
        if (imgUrl.current === response.data) {
          const requestData = {
            ...data,
            image_url: imgUrl.current,
            admin_member_id: memberId + '',
          };
  
          api
            .post(
              `${import.meta.env.VITE_BASE_URL}rooms/${memberId}/add`,
              requestData,
              {
                headers: {
                  Authorization: `Bearer ${token}`,
                },
              }
            )
            .then((response) => {
              // 요청 성공 시 처리
              console.log('@@@스터디 성공@@@');
              console.log(response.data);
              navigate('/');
              window.location.reload(); // 페이지 리로드
            })
            .catch((error) => {
              // 요청 실패 시 처리
              console.error('게시물 애러');
            });
        }
      })
      .catch((error) => {
        // 요청 실패 시 처리
        console.log('파일 애러');
        console.log(error);
        setError(true);
      });
  };
  

  const handleSubmit = (data: any) => {
    sendFormData(data);
  };
  const isLoading = false;

  // 수동
  const handleFileUpload = (e: any) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('image', selectedFile);
    console.log(formData);
    console.log(memberId);

    api
      .post(`${import.meta.env.VITE_BASE_URL}thumbnail`, formData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'multipart/form-data',
        },
      })
      .then((response) => {
        // 요청 성공 시 처리
        console.log('성공');
        console.log(response.data);
        setError(false);
        imgUrl.current = response.data
      })
      .catch((error) => {
        // 요청 실패 시 처리
        console.log('애러');
        console.log(error);
        setError(true);
      });
  };

  return (
    <CreateRoomPageContainer>
      <Header></Header>
      <Container>
        <div>
          <h1>스터디 만들기</h1>
          <RoomForm
            formError={formError}
            setError={setError}
            onSubmit={handleSubmit}
            isLoading={isLoading}
            setSelectedFile={setSelectedFile}
            handleFileUpload={handleFileUpload}
          ></RoomForm>
        </div>
      </Container>
    </CreateRoomPageContainer>
  );
};

export default CreateRoomPage;
